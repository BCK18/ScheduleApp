package com.example.scheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EmployeeViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_view)


        val btn_Add = findViewById<Button>(R.id.btn_Add) as Button
        val btn_Logout = findViewById<Button>(R.id.btn_Logout) as Button
        val menuBtn = findViewById<Button>(R.id.menuBtn) as Button
        val rv_Employee_Recycler = findViewById<RecyclerView>(R.id.rv_Employee_Recycler) as RecyclerView
        val btn_inactive=findViewById<Button>(R.id.inactive_btn) as Button

        var dataBaseHelper = DataBaseHelper(applicationContext)
        var employees = dataBaseHelper.getEveryone(1)
        

        rv_Employee_Recycler.adapter = EmployeeRecyclerAdapter(this, employees)
        rv_Employee_Recycler.layoutManager = LinearLayoutManager(this)

        //var employeeArrayAdapter = ArrayAdapter<Employee>(applicationContext, android.R.item_employee.xml.simple_list_item_1, dataBaseHelper.getEveryone());

        //ShowEmployeesOnListView(employeeArrayAdapter, dataBaseHelper, lv_Employee_List)

        btn_inactive.setOnClickListener {
            val intent=Intent(this,Inactive::class.java)
            startActivity(intent)
        }

        btn_Add.setOnClickListener {

            val intent = Intent(this, EmployeeAddActivity::class.java)
            startActivity(intent)
        }


        btn_Logout.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        menuBtn.setOnClickListener {

            val popupMenu = PopupMenu(this@EmployeeViewActivity, menuBtn)

            popupMenu.menuInflater.inflate(R.menu.schedule_app_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { menuItem ->

                if (menuItem.title == "Schedules"){
                    val intent = Intent(this, MonthViewActivity::class.java)
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

    /*
    private fun ShowEmployeesOnListView(
        employeeArrayAdapter: ArrayAdapter<Employee>,
        dataBaseHelper: DataBaseHelper,
        lv_Employee_List: ListView
    ): ArrayAdapter<Employee> {
        var employeeArrayAdapter1 = employeeArrayAdapter
        employeeArrayAdapter1 = ArrayAdapter<Employee>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            dataBaseHelper.getEveryone()
        );
        lv_Employee_List.setAdapter(employeeArrayAdapter1);
        return employeeArrayAdapter1
    }
    */
}