package com.example.guesthaven

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*

class NewDataActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var InputNama: EditText
    private lateinit var InputHarga: EditText
    private lateinit var InputLokasi: EditText
    private lateinit var BtnTambah: Button
    private lateinit var imageView: ImageView

    private var selectedImageUri: Uri? = null
    private lateinit var storageReference: StorageReference

    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_data)

        InputNama = findViewById(R.id.etNama)
        InputHarga = findViewById(R.id.etHarga)
        InputLokasi = findViewById(R.id.etLokasi)
        BtnTambah = findViewById(R.id.btnAdd)
        imageView = findViewById(R.id.imageView)

        BtnTambah.setOnClickListener(this)
        imageView.setOnClickListener(this)

        storageReference = FirebaseStorage.getInstance().reference
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnAdd -> sendDataToFirebase()
            R.id.imageView -> pickImage()
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun sendDataToFirebase() {
        // Retrieve data from EditText fields
        val Nama: String = InputNama.text.toString().trim()
        val Lokasi: String = InputLokasi.text.toString().trim()
        val Harga: String = InputHarga.text.toString().trim()

        // Validate input fields
        if (Nama.isEmpty() || Lokasi.isEmpty() || Harga.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Semua field dan gambar harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        // Upload image to Firebase Storage
        val imageFileName = UUID.randomUUID().toString() + ".jpg"
        val imageRef = storageReference.child("images/$imageFileName")
        val uploadTask = imageRef.putFile(selectedImageUri!!)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result

                // Save data to Firebase Database
                val ref = FirebaseDatabase.getInstance().getReference("catalog")
                val idbooking = ref.push().key
                val mhs = Database(Nama, Lokasi, Harga, downloadUri.toString())
                idbooking?.let {
                    ref.child(it).setValue(mhs).addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Data Berhasil Ditambahkan",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Gagal.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                // Handle failures
                Toast.makeText(
                    applicationContext,
                    "Gagal mengupload gambar. Silakan coba lagi.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            imageView.setImageURI(selectedImageUri)
        }
    }
}