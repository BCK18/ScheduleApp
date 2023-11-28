package com.example.scheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduleapp.CalendarUtils.daysInMonthArray
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class MonthViewActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {

    private lateinit var monthYearText: TextView
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var menuBtn: Button
    //private lateinit var selectedDate: LocalDate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_month_view)
        initWidgets()
        CalendarUtils.selectedDate = LocalDate.now()
        setMonthView();

        menuBtn.setOnClickListener {

            val popupMenu = PopupMenu(this, menuBtn)

            popupMenu.menuInflater.inflate(R.menu.schedule_app_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->

                if (menuItem.title == "Employees"){
                    val intent = Intent(this, EmployeeViewActivity::class.java)
                    startActivity(intent)
                }
                if (menuItem.title == "Logout"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            popupMenu.show()
        }
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
        menuBtn = findViewById(R.id.menuBtn)
    }

    private fun setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate))
        var daysInMonth: ArrayList<LocalDate> = daysInMonthArray(CalendarUtils.selectedDate)

        var calendarAdapter: CalendarAdapter = CalendarAdapter(daysInMonth, this)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(getApplicationContext(), 7)
        calendarRecyclerView.setLayoutManager(layoutManager)
        calendarRecyclerView.setAdapter(calendarAdapter)
    }

    fun monthYearFromDate(date: LocalDate): String? {

        val formatter: DateTimeFormatter  = DateTimeFormatter.ofPattern("MMMM yyyy")

        return date.format(formatter)
    }

    fun previousMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1)
        setMonthView()
    }

    fun nextMonthAction(view: View) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1)
        setMonthView()
    }

    fun weeklyAction(view: View) {

        startActivity(Intent(this, WeekViewActivity::class.java))
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            Log.i("Date: %s", date.toString());
            setMonthView();
        }
    }
}