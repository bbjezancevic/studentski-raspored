package com.example.studentski_raspored.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.studentski_raspored.R
import com.example.studentski_raspored.fragments.DayFragment
import com.example.studentski_raspored.models.Entry
import java.util.*

class EntryRecyclerAdapter(private val itemList: MutableList<Entry>, private val day: String) :
    RecyclerView.Adapter<EntryRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemList[position]

        holder.name.text = data.name
        holder.location.text = data.location
        holder.time.text = data.time

        holder.name.setOnClickListener {
            Log.e("day", day)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.tv_entry_name)
        val location: TextView = item.findViewById(R.id.tv_entry_location)
        val time: TextView = item.findViewById(R.id.tv_entry_time)
    }

    fun dataAdded(entry: MutableList<Entry>) {
        val difference = entry.find { !itemList.contains(it) }

        if (difference != null) {
            itemList.add(difference)
            notifyItemInserted(itemCount-1)
        }
    }
}