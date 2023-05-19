package com.example.studentski_raspored.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentski_raspored.R
import com.example.studentski_raspored.adapters.SubjectsRecyclerAdapter
import com.example.studentski_raspored.helpers.Firebase

class SubjectActivity : AppCompatActivity() {
    @SuppressLint("StaticFieldLeak")
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var subjectAdapter: SubjectsRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        subjectAdapter = SubjectsRecyclerAdapter(mutableListOf(), this)
        innitView()
    }

    private fun innitView() {
        findViewById<RecyclerView>(R.id.rv_subjects).apply {
            layoutManager = LinearLayoutManager(this@SubjectActivity)
            adapter = subjectAdapter
        }

        Firebase.getSubjects()
    }
}