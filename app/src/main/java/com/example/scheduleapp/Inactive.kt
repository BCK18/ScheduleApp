package com.example.scheduleapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Inactive : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inactive)
        val rv_Employee_Recycler = findViewById<RecyclerView>(R.id.inactive) as RecyclerView
        val inactive_back=findViewById<Button>(R.id.inactive_back) as Button
        var db=DataBaseHelper(applicationContext)
        var employees = db.getEveryone(2)
        rv_Employee_Recycler.adapter = RecyclerviewInactive(this, employees)
        rv_Employee_Recycler.layoutManager = LinearLayoutManager(this)
        inactive_back.setOnClickListener {
            val intent= Intent(this,EmployeeViewActivity::class.java)
            startActivity(intent)

        }



    }
}