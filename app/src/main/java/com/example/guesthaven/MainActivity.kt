package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exploreButton: Button = findViewById(R.id.exploreButton)

//        exploreButton.setOnClickListener {
//            // Handle button click, for example, navigate to another activity
//            val intent = Intent(this, ExploreActivity::class.java)
//            startActivity(intent)
//        }
    }
}