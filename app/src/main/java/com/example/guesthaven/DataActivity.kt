package com.example.guesthaven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        homeRecyclerView = findViewById(R.id.homeList)
        val homeArrayList = ArrayList<Database>() // Local variable for data list
        adapter = DataAdapter(homeArrayList)
        homeRecyclerView.adapter = adapter
        homeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize DatabaseReference
        dbRef = FirebaseDatabase.getInstance().reference.child("catalog")
        homeArrayList.add(Database("Sample Home", "Sample Location", "50000"))
        adapter.notifyDataSetChanged()
        // Add ValueEventListener to read data from Firebase
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newList = mutableListOf<Database>()
                for (dataSnapshot in snapshot.children) {
                    val home = Database(
                        dataSnapshot.child("name").getValue(String::class.java) ?: "",
                        dataSnapshot.child("location").getValue(String::class.java) ?: "",
                        dataSnapshot.child("price").getValue(String::class.java) ?: ""
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