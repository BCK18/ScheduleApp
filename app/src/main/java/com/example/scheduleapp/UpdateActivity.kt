package com.example.scheduleapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class UpdateActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation", "UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        // Get the Parcelable extra from the intent
        val receivedEmployee = intent.getSerializableExtra("employee",Employee::class.java)

        // Initialize your UI components
        val et_First_Name = findViewById<EditText>(R.id.et_First_Name2)
        val et_Last_Name = findViewById<EditText>(R.id.et_Last_Name2)
        val et_Phone_Number = findViewById<EditText>(R.id.et_Phone_Number2)
        val et_Email = findViewById<EditText>(R.id.et_Email2)
        val sw_Opening = findViewById<Switch>(R.id.sw_Opening2)
        val sw_Closing = findViewById<Switch>(R.id.sw_Closing2)
        val sw_Prefs_Mornings = findViewById<Switch>(R.id.sw_Prefs_Mornings2)
        val sw_Prefs_Afternoons = findViewById<Switch>(R.id.sw_Prefs_Afternoons2)
        val sw_Prefs_Evenings = findViewById<Switch>(R.id.sw_Prefs_Evenings2)
        val sw_Prefs_Weekends = findViewById<Switch>(R.id.sw_Prefs_Weekends2)
        val btn_update = findViewById<Button>(R.id.btn_update2)
        val btn_Last = findViewById<Button>(R.id.btn_last2)
        val title=findViewById<TextView>(R.id.tv_AddEmployee2)




        // Use the getIntentData function to handle the receivedEmployee data
        if (receivedEmployee!=null) {
            // Set the data to the respective UI components
            et_First_Name.setText(receivedEmployee.first_Name)
            et_Last_Name.setText(receivedEmployee.last_Name)
            et_Phone_Number.setText(receivedEmployee.phone_Number)
            et_Email.setText(receivedEmployee.email)

            sw_Opening.isChecked = receivedEmployee.opening ?: false
            sw_Closing.isChecked = receivedEmployee.closing ?: false
            sw_Prefs_Mornings.isChecked = receivedEmployee.mornings ?: false
            sw_Prefs_Afternoons.isChecked = receivedEmployee.afternoons ?: false
            sw_Prefs_Evenings.isChecked = receivedEmployee.evenings ?: false
            sw_Prefs_Weekends.isChecked = receivedEmployee.weekends ?: false
        title.text= buildString { append(receivedEmployee.first_Name)
        append(" ")
            append(receivedEmployee.last_Name)
        }
        }


        // Add your button click listeners and other logic here
        btn_update.setOnClickListener {
            // Retrieve user-entered data from UI components
            val updatedFirstName = et_First_Name.text.toString()
            val updatedLastName = et_Last_Name.text.toString()
            val updatedPhoneNumber = et_Phone_Number.text.toString()
            val updatedEmail = et_Email.text.toString()
            val updatedOpening = sw_Opening.isChecked
            val updatedClosing = sw_Closing.isChecked
            val updatedMornings = sw_Prefs_Mornings.isChecked
            val updatedAfternoons = sw_Prefs_Afternoons.isChecked
            val updatedEvenings = sw_Prefs_Evenings.isChecked
            val updatedWeekends = sw_Prefs_Weekends.isChecked
            val updatedStatus = receivedEmployee?.status ?: true
            // Create a new Employee object with the updated data
            val updatedEmployee = Employee(
                receivedEmployee?.id ?: 0,  // Assuming id is non-null
                updatedFirstName,
                updatedLastName,
                updatedPhoneNumber,
                updatedEmail,
                updatedOpening,
                updatedClosing,
                updatedMornings,
                updatedAfternoons,
                updatedEvenings,
                updatedWeekends,
                updatedStatus
            )

            // Handle update using the updatedEmployee object
            val db = DataBaseHelper(this)
            db.updateData(updatedEmployee)
            val intent = Intent(this, EmployeeViewActivity::class.java)
            startActivity(intent)
        }


        btn_Last.setOnClickListener {
            val intent = Intent(this, EmployeeViewActivity::class.java)
            startActivity(intent)
        }
    }



}
