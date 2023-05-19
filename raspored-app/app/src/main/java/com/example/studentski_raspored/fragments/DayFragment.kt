package com.example.studentski_raspored.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentski_raspored.adapters.EntryRecyclerAdapter
import com.example.studentski_raspored.databinding.FragmentDayBinding
import com.example.studentski_raspored.helpers.DataHolder
import com.example.studentski_raspored.helpers.Firebase

class DayFragment : Fragment() {
    private lateinit var dayBinding: FragmentDayBinding
    private var FRAG_DATE = "Date"
    lateinit var entryAdapter: EntryRecyclerAdapter

    companion object {
        fun newInstance(date: String?) = DayFragment().apply {
            arguments = Bundle(1).apply {
                if (date != null) {
                    FRAG_DATE = date
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        dayBinding = FragmentDayBinding.inflate(inflater, container, false)

        setup()
        return dayBinding.root
    }

    private fun setup() {
        //dohvacanje podataka iz baze po datumu: arguments?.getString(FRAG_DATE) ?: FRAG_DATE
        entryAdapter = EntryRecyclerAdapter(mutableListOf(), FRAG_DATE)
        dayBinding.rvClasses.layoutManager = LinearLayoutManager(context)
        dayBinding.rvClasses.adapter = entryAdapter

        val tempSubjs: List<String> = DataHolder.getUserSubjects().split(",")
        Firebase.getEntries(FRAG_DATE, entryAdapter, tempSubjs)
    }
}