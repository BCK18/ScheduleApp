package com.example.scheduleapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class EmployeeRecyclerAdapter(private val context: Context, private val employees : List<Employee>) : RecyclerView.Adapter<EmployeeRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val constraintLayout = itemView.findViewById<ConstraintLayout>(R.id.employee_row)
        private val tv_Name = itemView.findViewById(R.id.tv_Name) as TextView
        private val tv_ID = itemView.findViewById(R.id.tv_ID) as TextView
        private val tv_Additional = itemView.findViewById(R.id.tv_Additional) as TextView

        fun bind(employee: Employee) {

            tv_Name.text = buildString {
                append(employee.firstName)
                append(" ")
                append(employee.lastName)
            }

            tv_ID.text = buildString {
                append("Employee ID: ")
                append(employee.id)
            }

            tv_Additional.text =
                buildString {
                    append("Phone Number: ")
                    append(employee.phoneNumber)
                    append(", Email: ")
                    append(employee.email)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return employees.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee)
        holder.constraintLayout.setOnClickListener {
            showPopupMenu(it, employee)
        }

    }

    private fun showPopupMenu(view: View, employee: Employee) {
        val popupMenu = PopupMenu(view.context, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.modify_delete, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.modify -> {
                    // Handle modify action
                    val intent = Intent(view.context, UpdateActivity::class.java)
                    intent.putExtra("employee", employee)
                    view.context.startActivity(intent)
                    true
                }

                R.id.delete -> {
                    val deleteEmployeeHelper = deleteEmployee(view.context, "SchedulingApp.db", null, 1)
                    deleteEmployeeHelper.deleteEmployee(employee.id)
                    val intent = Intent(view.context, EmployeeViewActivity::class.java)
                    view.context.startActivity(intent)

                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }
}

