package com.example.scheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class EmployeeAddActivity : AppCompatActivity() {

    private val TAG: String = "EmployeeAddActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_add)

        val et_First_Name = findViewById<EditText>(R.id.et_First_Name) as EditText
        val et_Last_Name = findViewById<EditText>(R.id.et_Last_Name) as EditText
        val et_Phone_Number = findViewById<EditText>(R.id.et_Phone_Number) as EditText
        val et_Email = findViewById<EditText>(R.id.et_Email) as EditText
        val sw_Opening = findViewById<Switch>(R.id.sw_Opening) as Switch
        val sw_Closing = findViewById<Switch>(R.id.sw_Closing) as Switch
        val sw_Prefs_Mornings = findViewById<Switch>(R.id.sw_Prefs_Mornings) as Switch
        val sw_Prefs_Afternoons = findViewById<Switch>(R.id.sw_Prefs_Afternoons) as Switch
        val sw_Prefs_Evenings = findViewById<Switch>(R.id.sw_Prefs_Evenings) as Switch
        val sw_Prefs_Weekends = findViewById<Switch>(R.id.sw_Prefs_Weekends) as Switch
        val btn_Confirm = findViewById<Button>(R.id.btn_Confirm) as Button
        val btn_Last = findViewById<Button>(R.id.btn_Last) as Button

        var dataBaseHelper = DataBaseHelper(applicationContext)

        var employeeArrayAdapter = ArrayAdapter<Employee>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            dataBaseHelper.getEveryone(1)
        )

        var newEmployee: Employee;

        btn_Confirm.setOnClickListener {

            if ((et_First_Name.text.toString() != "") and (et_Last_Name.text.toString() != "")
                and (et_Phone_Number.text.toString() != "") and (et_Email.text.toString() != "")) {
            try {
                newEmployee = Employee(
                    -1,
                    et_First_Name.text.toString(),
                    et_Last_Name.text.toString(),
                    et_Phone_Number.text.toString(),
                    et_Email.text.toString(),
                    sw_Opening.isChecked,
                    sw_Closing.isChecked,
                    sw_Prefs_Mornings.isChecked,
                    sw_Prefs_Afternoons.isChecked,
                    sw_Prefs_Evenings.isChecked,
                    sw_Prefs_Weekends.isChecked,
                    true
                )

                Log.i(TAG, "here")

                Toast.makeText(applicationContext, newEmployee.toString(), Toast.LENGTH_SHORT)
                    .show()
            } catch (e: Exception) {

                Toast.makeText(applicationContext, "Error creating employee", Toast.LENGTH_SHORT)
                    .show()

                newEmployee = Employee(
                    -1, "error", "error", "error", "error",
                    false, false, false, false, false, false,false
                )
            }
                val intent = Intent(this, EmployeeViewActivity::class.java)
                startActivity(intent)

                var success: Boolean = dataBaseHelper.addOne(newEmployee)

                Toast.makeText(applicationContext, "Success=" + success, Toast.LENGTH_SHORT).show()
            }

            else {
                Toast.makeText(applicationContext, "Employee add error", Toast.LENGTH_SHORT).show()
            }
        }

        btn_Last.setOnClickListener {

            val intent = Intent(this, EmployeeViewActivity::class.java)
            startActivity(intent)
        }
    }
}