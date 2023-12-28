package com.example.guesthaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (private val homeList: ArrayList<Database>) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = homeList[position]

        holder.homeName.text = currentitem.name
        holder.location.text = currentitem.location
        holder.price.text = currentitem.price
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeName: TextView = itemView.findViewById(R.id.tvhomeName)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val location: TextView = itemView.findViewById(R.id.tvLocation)
    }

    // Function to update the adapter when the data changes
    fun updateData(newList: List<Database>) {
        homeList.clear()
        homeList.addAll(newList)
        notifyDataSetChanged()
    }
}