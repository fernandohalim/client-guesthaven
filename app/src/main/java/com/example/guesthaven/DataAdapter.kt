package com.example.guesthaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DataAdapter(private val homeList: ArrayList<Database>, private val onItemClick: (Database) -> Unit) :
    RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = homeList[position]

        Glide.with(holder.itemView.context)
            .load(currentitem.imagePath)
            .into(holder.imageView)

        holder.homeName.text = currentitem.name
        holder.location.text = currentitem.location
        holder.price.text = currentitem.price

        holder.itemView.setOnClickListener {
            onItemClick(currentitem)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val homeName: TextView = itemView.findViewById(R.id.tvhomeName)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val location: TextView = itemView.findViewById(R.id.tvLocation)
    }

    fun updateData(newList: List<Database>) {
        homeList.clear()
        homeList.addAll(newList)
        notifyDataSetChanged()
    }
}