package com.example.scheduleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ScheduleApp)
        setContentView(R.layout.activity_main)

        var dataBaseHelper = DataBaseHelper(applicationContext)

        var passwords = dataBaseHelper.getPassword();

        if (passwords.size > 0) {

            setContentView(R.layout.activity_main)

            val et_Password = findViewById<EditText>(R.id.et_Password) as EditText
            val btn_Login = findViewById<Button>(R.id.btn_Login) as Button

            btn_Login.setOnClickListener {

                if (et_Password.text.toString().hash() == passwords.get(0).toString()) {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()

                    // Create an Intent to start the MainActivity
                    val intent = Intent(this, EmployeeViewActivity::class.java)
                    startActivity(intent)
                } else {
                    // Remove the below line before release.
                    Toast.makeText(this, String.format("Hashed Password: %s", passwords.get(0)), Toast.LENGTH_LONG).show()
                    //Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show()
                }
            }
        }

        else {

            setContentView(R.layout.first_login)

            val et_NewPassword1 = findViewById<EditText>(R.id.et_NewPassword1) as EditText
            val et_NewPassword2 = findViewById<EditText>(R.id.et_NewPassword2) as EditText
            val btn_SetPassword = findViewById<Button>(R.id.btn_SetPassword) as Button


            btn_SetPassword.setOnClickListener {

                if (et_NewPassword1.text.toString() == et_NewPassword2.text.toString()) {

                    dataBaseHelper.addPassword(et_NewPassword1.text.toString().hash())

                    Toast.makeText(this, "Password Set Successfully", Toast.LENGTH_LONG).show()

                    // Create an Intent to start the MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Passwords Do Not Match", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
