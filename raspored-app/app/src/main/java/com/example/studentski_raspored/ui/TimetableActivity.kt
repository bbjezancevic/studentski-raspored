package com.example.studentski_raspored.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.studentski_raspored.adapters.FragmentAdapter
import com.example.studentski_raspored.databinding.ActivityTimetableBinding
import com.example.studentski_raspored.fragments.DayFragment
import java.text.SimpleDateFormat
import java.util.*

class TimetableActivity : AppCompatActivity() {
    private lateinit var timetableBinding: ActivityTimetableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timetableBinding = ActivityTimetableBinding.inflate(layoutInflater)
        setContentView(timetableBinding.root)

        setupUI()
    }

    private fun setupUI() {
        val tempDays = mutableMapOf("MONDAY" to "1",
            "TUESDAY" to "2",
            "WEDNESDAY" to "3",
            "THURSDAY" to "4",
            "FRIDAY" to "5",
            "SATURDAY" to "6")
        var currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        currentDate = currentDate.replace("/", ".")

        val calendar = Calendar.getInstance()
        when (calendar[Calendar.DAY_OF_WEEK]) {
            Calendar.SUNDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
            }
            Calendar.MONDAY -> {
                tempDays["MONDAY"] = currentDate
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")}
            Calendar.TUESDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, -1)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = currentDate
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
            }
            Calendar.WEDNESDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, -2)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  currentDate
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
            }
            Calendar.THURSDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, -3)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = currentDate
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")}
            Calendar.FRIDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, -4)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = currentDate
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
            }
            Calendar.SATURDAY -> {
                calendar.add(Calendar.DAY_OF_WEEK, -5)
                tempDays["MONDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["TUESDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["WEDNESDAY"] =  SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["THURSDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["FRIDAY"] = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time).replace("/", ".")
                calendar.add(Calendar.DAY_OF_WEEK, +1)
                tempDays["SATURDAY"] = currentDate
            }
        }

        val adapter = FragmentAdapter(this)
        var currentDay = 0
        timetableBinding.vp2Dates.currentItem = currentDay
        timetableBinding.tvDate.text = tempDays.values.elementAt(currentDay)
        timetableBinding.tvDay.text = tempDays.keys.elementAt(currentDay)

        for (day in tempDays) {
            val myFragment: DayFragment = DayFragment.newInstance(day.value)
            adapter.addFragment(myFragment)
        }

        timetableBinding.ivDateLeft.setOnClickListener {
            if (currentDay > 0 && currentDay < tempDays.size) {
                currentDay -= 1
                timetableBinding.vp2Dates.currentItem = currentDay
                timetableBinding.tvDate.text = tempDays.values.elementAt(currentDay)
                timetableBinding.tvDay.text = tempDays.keys.elementAt(currentDay)
            }
        }

        timetableBinding.ivDateRight.setOnClickListener {
            if (currentDay < tempDays.size - 1) {
                currentDay += 1
                timetableBinding.vp2Dates.currentItem = currentDay
                timetableBinding.tvDate.text = tempDays.values.elementAt(currentDay)
                timetableBinding.tvDay.text = tempDays.keys.elementAt(currentDay)
            }
        }

        val viewPager2: ViewPager2 = timetableBinding.vp2Dates
        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentDay = position
                timetableBinding.tvDate.text = tempDays.values.elementAt(currentDay)
                timetableBinding.tvDay.text = tempDays.keys.elementAt(currentDay)

                super.onPageSelected(position)
            }
        })
    }
}