package com.example.scheduleapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.CalendarUtils.daysInMonthArray
import com.example.scheduleapp.CalendarUtils.daysInWeekArray
import com.example.scheduleapp.CalendarUtils.monthYearFromDate
import java.time.LocalDate


class WeekViewActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var eventListView: ListView // !!
    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_view)
        initWidgets()
        //CalendarUtils.selectedDate = LocalDate.now()
        setWeekView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById<RecyclerView>(R.id.calendarRecyclerView)
        monthYearText = findViewById<TextView>(R.id.monthYearTV)
        eventListView = findViewById<ListView>(R.id.eventListView)
        backBtn = findViewById<Button>(R.id.backBtn)
    }

    private fun setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate))
        var days: ArrayList<LocalDate> = daysInWeekArray(CalendarUtils.selectedDate)

        var calendarAdapter: CalendarAdapter = CalendarAdapter(days, this)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(getApplicationContext(), 7)
        calendarRecyclerView.setLayoutManager(layoutManager)
        calendarRecyclerView.setAdapter(calendarAdapter)
        setEventAdapter()
    }

    fun previousWeekAction(view: View) {

        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    fun nextWeekAction(view: View) {

        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }

    override fun onResume(){
        super.onResume()
        setEventAdapter()
    }

    fun setEventAdapter() { // !!

        var dataBaseHelper: DataBaseHelper = DataBaseHelper(getApplicationContext())

        var employee_list: ArrayList<Employee> = dataBaseHelper.getEveryone(1)

        var week = ScheduleAlgorithm.generateSchedule(employee_list, CalendarUtils.daysInWeekArray(CalendarUtils.selectedDate))

        var shifts: ArrayList<Shift> = ArrayList<Shift>()

        for (i in 0..(week.days.size - 1)){

            for (j in 0..(week.days.get(i).shifts.size - 1)){

                shifts.add(week.days.get(i).shifts.get(j))
            }
        }

        var day_shifts: ArrayList<Shift> = ArrayList<Shift>();

        for (i in 0 .. (shifts.size - 1)) {
            if (shifts.get(i).date == CalendarUtils.selectedDate) {
                day_shifts.add(shifts.get(i));
            }
        }

        //var dailyEvents: ArrayList<Event> = Event.eventsForDate(CalendarUtils.selectedDate)
        var shiftAdapter: ShiftAdapter = ShiftAdapter(getApplicationContext(), day_shifts)
        eventListView.setAdapter(shiftAdapter)
    }

    fun newEventAction(view: View) { // !!

        startActivity(Intent(this, EventEditActivity::class.java))
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }

    fun backWeekView(view: View) {

        val intent = Intent(this, MonthViewActivity::class.java)
        startActivity(intent)
    }
}