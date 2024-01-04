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

class ContactActivity : AppCompatActivity() {
    private lateinit var contactRecyclerView: RecyclerView
    private lateinit var contactAdapter: DataContactAdapter
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        contactRecyclerView = findViewById(R.id.contactList)
        val contactArrayList = ArrayList<DatabaseContact>()
        contactAdapter = DataContactAdapter(contactArrayList)
        contactRecyclerView.adapter = contactAdapter
        contactRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize DatabaseReference
        dbRef = FirebaseDatabase.getInstance().reference.child("contact")

        // Add ValueEventListener to read data from Firebase
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newList = mutableListOf<DatabaseContact>()
                for (dataSnapshot in snapshot.children) {
                    val order = DatabaseContact(
                        dataSnapshot.child("name").getValue(String::class.java) ?: "",
                        dataSnapshot.child("number").getValue(String::class.java) ?: "",
                        dataSnapshot.child("description").getValue(String::class.java) ?: "",
                    )
                    newList.add(order)
                }
                contactArrayList.addAll(newList) // Add new data
                contactAdapter.notifyDataSetChanged() // Notify adapter about the changes
            }


            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "onCancelled: $error")
            }
        })
    }
}