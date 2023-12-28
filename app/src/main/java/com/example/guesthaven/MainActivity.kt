package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btn_add : Button
    private lateinit var btn_get : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add = findViewById(R.id.btn_add)
        btn_get = findViewById(R.id.btn_get)

        btn_add.setOnClickListener{
            startActivity(Intent(this,NewDataActivity::class.java))
        }

        btn_get.setOnClickListener{
            startActivity(Intent(this,DataActivity::class.java))
        }
    }
}