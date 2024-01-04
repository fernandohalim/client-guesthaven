package com.example.guesthaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataContactAdapter(private val orderList: ArrayList<DatabaseContact>) : RecyclerView.Adapter<DataContactAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = orderList[position]

        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        holder.number.text = currentItem.number

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val number: TextView = itemView.findViewById(R.id.numberTextView)
        val description: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    fun updateData(newList: List<DatabaseContact>) {
        orderList.clear()
        orderList.addAll(newList)
        notifyDataSetChanged()
    }
}
