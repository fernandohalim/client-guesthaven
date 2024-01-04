package com.example.guesthaven

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var adapter: DataAdapter // Update adapter initialization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        homeRecyclerView = findViewById(R.id.homeList)
        val homeArrayList = ArrayList<Database>() // Local variable for data list
        adapter = DataAdapter(homeArrayList) { selectedData ->
            val intent = Intent(this@DataActivity, NewOrderActivity::class.java)
            intent.putExtra("nama", selectedData.name)
            intent.putExtra("lokasi", selectedData.location)
            intent.putExtra("harga", selectedData.price)
            startActivity(intent)
        }
        homeRecyclerView.adapter = adapter
        homeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize DatabaseReference
        dbRef = FirebaseDatabase.getInstance().reference.child("catalog")
        // Add ValueEventListener to read data from Firebase
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newList = mutableListOf<Database>()
                for (dataSnapshot in snapshot.children) {
                    val home = Database(
                        dataSnapshot.child("name").getValue(String::class.java) ?: "",
                        dataSnapshot.child("location").getValue(String::class.java) ?: "",
                        dataSnapshot.child("price").getValue(String::class.java) ?: "",
                        dataSnapshot.child("imagePath").getValue(String::class.java) ?: ""
                    )
                    newList.add(home)
                }
                homeArrayList.addAll(newList) // Add new data
                adapter.notifyDataSetChanged() // Notify adapter about the changes
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "onCancelled: $error")
                // Handle the error
            }
        })
    }
}