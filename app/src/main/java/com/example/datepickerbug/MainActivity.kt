package com.example.datepickerbug

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val dateFormatter = SimpleDateFormat.getDateInstance()
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val minDate = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(400)

        button = Button(this)
        button.text = generateButtonString(minDate)
        button.setOnClickListener {
            openDatePicker(minDate)
        }

        findViewById<ViewGroup>(android.R.id.content).addView(button)
    }

    private fun openDatePicker(minDate: Long) {
        val dialog = DatePickerDialog(this)
        dialog.datePicker.minDate = minDate
        dialog.setOnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            button.text = generateButtonString(datePicker.minDate, "$year-$month-$dayOfMonth")
        }
        dialog.show()
    }

    private fun generateButtonString(minDate: Long, selectedDate: String? = null): String {
        val minDateString = dateFormatter.format(minDate)
        return "minDate=$minDateString\nselectedDate=$selectedDate"
    }
}
