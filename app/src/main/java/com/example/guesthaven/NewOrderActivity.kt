package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NewOrderActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        val nama = intent.getStringExtra("nama") ?: ""
        val lokasi = intent.getStringExtra("lokasi") ?: ""
        val harga = intent.getStringExtra("harga") ?: ""

        val namaTextView: TextView = findViewById(R.id.namaTextView)
        val lokasiTextView: TextView = findViewById(R.id.lokasiTextView)
        val hargaTextView: TextView = findViewById(R.id.hargaTextView)
        val pemesanNameEditText: EditText = findViewById(R.id.pemesanNameEditText)
        val noTelpEditText: EditText = findViewById(R.id.noTelpEditText)
        val submitOrderButton: Button = findViewById(R.id.submitOrderButton)

        namaTextView.text = "Nama: $nama"
        lokasiTextView.text = "Lokasi: $lokasi"
        hargaTextView.text = "Harga: $harga"

        dbRef = FirebaseDatabase.getInstance().getReference("booking")

        submitOrderButton.setOnClickListener {
            val pemesanName = pemesanNameEditText.text.toString().trim()
            val noTelp = noTelpEditText.text.toString().trim()

            if (pemesanName.isEmpty() || noTelp.isEmpty()) {
                Toast.makeText(this@NewOrderActivity, "Nama pemesan dan no. telepon harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val dataOrder = DatabaseOrder(nama, lokasi, pemesanName, noTelp, timestamp)
            dbRef.push().setValue(dataOrder)
            Toast.makeText(this@NewOrderActivity, "Order Berhasil!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@NewOrderActivity, MainActivity::class.java))
            finish()
        }
    }
}
