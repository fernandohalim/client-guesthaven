package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class NewDataActivity : AppCompatActivity(), OnClickListener {
    private lateinit var input_name: EditText
    private lateinit var input_price: EditText
    private lateinit var input_location: EditText
    private lateinit var btn_submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_data)

        input_name = findViewById(R.id.name)
        input_price = findViewById(R.id.price)
        input_location = findViewById(R.id.location)
        btn_submit = findViewById(R.id.submit)

        btn_submit.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.submit -> sendDataToFirebase()
        }
    }

    private fun sendDataToFirebase() {
        // Retrieve data from EditText fields
        val name: String = input_name.text.toString().trim()
        val location: String = input_location.text.toString().trim()
        val price: String = input_price.text.toString().trim()

        // Validate input fields
        if (name.isEmpty()) {
            input_name.error = "Nama tidak boleh Kosong"
            return
        }
        if (location.isEmpty()) {
            input_location.error = "Lokasi tidak boleh Kosong"
            return
        }
        if (price.isEmpty()) {
            input_price.error = "Harga tidak boleh Kosong"
            return
        }


        // Save data to Firebase Database
        val ref = FirebaseDatabase.getInstance().getReference("catalog")
        val id = ref.push().key
        val mhs = Database(name,location,price)
        id?.let {
            ref.child(it).setValue(mhs).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Data Berhasil Ditambah",
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
    }
}