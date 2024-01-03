package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText


class OrderActivity : AppCompatActivity() {
    private lateinit var txtnama : EditText
    private lateinit var txtkamar : EditText
    private lateinit var btnsumbit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        txtnama = findViewById(R.id.et_nama)
        txtkamar = findViewById(R.id.et_kamar)
        btnsumbit = findViewById(R.id.bt_submit)

        btnsumbit.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        }
    }
