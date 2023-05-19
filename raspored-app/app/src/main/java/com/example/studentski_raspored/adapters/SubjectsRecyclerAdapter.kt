package com.example.studentski_raspored.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.studentski_raspored.R
import com.example.studentski_raspored.fragments.DayFragment
import com.example.studentski_raspored.helpers.DataHolder
import com.example.studentski_raspored.helpers.Firebase
import com.example.studentski_raspored.models.Subject
import com.example.studentski_raspored.ui.SubjectActivity

class SubjectsRecyclerAdapter(private val itemList: MutableList<Subject>, private val context: Context) :
    RecyclerView.Adapter<SubjectsRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemList[position]

        holder.name.text = data.name
        holder.year.text = data.year
        holder.semester.text = data.semester

        var subjects1: String = DataHolder.getUserSubjects()
        val tempSubjs1: List<String> = subjects1.split(",")
        holder.isChecked.isChecked = tempSubjs1.contains(data.id)

        holder.isChecked.setOnClickListener {
            var subjects: String = DataHolder.getUserSubjects()
            val tempSubjs: List<String> = subjects.split(",")

             if (holder.isChecked.isChecked){
                if (!tempSubjs.contains(data.id)) {
                    DataHolder.setUserSubjects(DataHolder.getUserSubjects() + "," + data.id)
                    Firebase.changeUserSubjects(DataHolder.getUserId(), DataHolder.getUserSubjects(), context as Activity)
                }
            }
            else {
                if (tempSubjs.contains(data.id)) {
                    var temp: String = DataHolder.getUserSubjects()
                    var temp2 = temp.replace("," + data.id, "")
                    DataHolder.setUserSubjects(temp2)
                    Firebase.changeUserSubjects(DataHolder.getUserId(), DataHolder.getUserSubjects(), context as Activity)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = item.findViewById(R.id.tv_subject_name)
        val year: TextView = item.findViewById(R.id.tv_subject_year)
        val semester: TextView = item.findViewById(R.id.tv_subject_semester)
        val isChecked: CheckBox = item.findViewById(R.id.chk1)
    }

    fun dataAdded(subject: MutableList<Subject>) {
        val difference = subject.find { !itemList.contains(it) }

        if (difference != null) {
            itemList.add(difference)
            notifyItemInserted(itemCount - 1)
        }
    }
}