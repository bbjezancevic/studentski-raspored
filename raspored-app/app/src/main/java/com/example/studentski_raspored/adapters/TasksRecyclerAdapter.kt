package com.example.studentski_raspored.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.studentski_raspored.R
import com.example.studentski_raspored.models.Task
import com.example.studentski_raspored.ui.MainActivity
import com.example.studentski_raspored.ui.SubjectActivity
import com.example.studentski_raspored.ui.TimetableActivity
import com.squareup.picasso.Picasso

class TasksRecyclerAdapter(private val itemList: MutableList<Task>, private val context: Context) :
    RecyclerView.Adapter<TasksRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemList[position]

        holder.title.text = data.title
        holder.description.text = data.description
        Picasso.get().load(data.url).into(holder.img)

        holder.layout.setOnClickListener {
            if (holder.title.text == "Subjects") {
                val intent = Intent(context, SubjectActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } else if (holder.title.text == "Timetable") {
                val intent = Intent(context, TimetableActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val img: ImageView = item.findViewById(R.id.iv_task_img)
        val title: TextView = item.findViewById(R.id.tv_task_title)
        val description: TextView = item.findViewById(R.id.tv_task_description)
        val layout: ConstraintLayout = item.findViewById(R.id.task_layout)
    }

    fun dataAdded(task: MutableList<Task>) {
        val difference = task.find { !itemList.contains(it) }

        if (difference != null) {
            itemList.add(difference)
            notifyItemInserted(itemCount - 1)
        }
    }
}