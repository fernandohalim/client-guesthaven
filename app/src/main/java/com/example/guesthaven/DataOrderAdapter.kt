package com.example.guesthaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataOrderAdapter(private val orderList: ArrayList<DatabaseOrder>) : RecyclerView.Adapter<DataOrderAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = orderList[position]

        holder.name.text = currentItem.name
        holder.location.text = currentItem.location
        holder.orderName.text = currentItem.order_name
        holder.number.text = currentItem.number
        holder.date.text = currentItem.date
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val location: TextView = itemView.findViewById(R.id.locationTextView)
        val orderName: TextView = itemView.findViewById(R.id.ordernameTextView)
        val number: TextView = itemView.findViewById(R.id.numberTextView)
        val date: TextView = itemView.findViewById(R.id.dateTextView)
    }

    // Function to update the adapter when the data changes
    fun updateData(newList: List<DatabaseOrder>) {
        orderList.clear()
        orderList.addAll(newList)
        notifyDataSetChanged()
    }
}

