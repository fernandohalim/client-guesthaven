package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btn_add : Button
    private lateinit var btn_get : Button
    private lateinit var btn_order : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add = findViewById(R.id.btn_add)
        btn_get = findViewById(R.id.btn_get)
        btn_order = findViewById(R.id.btn_ord)

        btn_add.setOnClickListener{
            startActivity(Intent(this,NewDataActivity::class.java))
        }

        btn_get.setOnClickListener{
            startActivity(Intent(this,DataActivity::class.java))
        }

        btn_order.setOnClickListener {
            startActivity(Intent(this,OrderActivity::class.java))
        }
    }
}