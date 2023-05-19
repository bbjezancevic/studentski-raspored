package com.example.studentski_raspored.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentski_raspored.R
import com.example.studentski_raspored.adapters.TasksRecyclerAdapter
import com.example.studentski_raspored.helpers.Firebase

class MainActivity : AppCompatActivity() {
    @SuppressLint("StaticFieldLeak")
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var taskAdapter: TasksRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TasksRecyclerAdapter(mutableListOf(), this)
        innitView()
    }

    private fun innitView() {
        findViewById<RecyclerView>(R.id.rv_tasks).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        Firebase.getTasks()
    }


}
