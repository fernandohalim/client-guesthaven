package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    private lateinit var btnlogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin = findViewById(R.id.blogin)
        btnlogin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}