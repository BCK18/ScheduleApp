package com.example.scheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.LocalTime

class EventEditActivity : AppCompatActivity() {

    private lateinit var eventNameET: EditText
    private lateinit var eventDateTV: TextView
    private lateinit var eventTimeTV: TextView
    private lateinit var time: LocalTime
    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_edit)

        initWidgets();
        time = LocalTime.now()
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate))
        eventTimeTV.setText("Date: " + CalendarUtils.formattedTime(time))
    }

    private fun initWidgets() {
        eventNameET = findViewById<EditText>(R.id.eventNameET)
        eventDateTV = findViewById<TextView>(R.id.eventDateTV)
        eventTimeTV = findViewById<TextView>(R.id.eventTimeTV)
        backBtn = findViewById<Button>(R.id.backBtn)
    }

    fun saveEventAction(view: View) {

        var eventName: String? = eventNameET.getText().toString()

        var newEvent: Event = Event(eventName, CalendarUtils.selectedDate, time)

        Event.eventsList.add(newEvent)

        finish()
    }

    fun backEventEdit(view: View) {

        val intent = Intent(this, WeekViewActivity::class.java)
        startActivity(intent)
    }
}