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

class NewContactActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val numberTextView: TextView = findViewById(R.id.numberTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        dbRef = FirebaseDatabase.getInstance().getReference("contact")

        btnSubmit.setOnClickListener{
            val name = nameTextView.text.toString().trim()
            val number = numberTextView.text.toString().trim()
            val description = descriptionTextView.text.toString().trim()

            val dataKeluhan = DatabaseContact(name, number, description)
            dbRef.push().setValue(dataKeluhan)
            Toast.makeText(this@NewContactActivity, "Pesan Berhasil Dikirim", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@NewContactActivity, MainActivity::class.java))
        }

    }
}