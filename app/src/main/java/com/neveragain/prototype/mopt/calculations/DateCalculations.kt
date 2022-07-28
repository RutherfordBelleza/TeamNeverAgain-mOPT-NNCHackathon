package com.neveragain.prototype.mopt.calculations

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class DateCalculations {

    companion object {
        val formDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

        fun createDateListener(
            context: Context,
            maxDate: Date?,
            minDate: Date?
        ): View.OnClickListener {
            //General DateDialog Listener
            return View.OnClickListener { view ->
                val newCalendar = Calendar.getInstance()
                val currentView: EditText = view as EditText
                val viewText = view.text.toString()
                if (viewText.isNotBlank()) {
                    val date = formDateStringToDate(viewText)
                    newCalendar.time = date
                }
                val tempDay = newCalendar.get(Calendar.DAY_OF_MONTH)
                val tempMonth = newCalendar.get(Calendar.MONTH)
                val tempYear = newCalendar.get(Calendar.YEAR)
                val datePicker = DatePickerDialog(context, { _, year, monthOfYear, dayOfMonth ->

                    newCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    newCalendar.set(Calendar.MONTH, monthOfYear)

                    newCalendar.set(Calendar.YEAR, year)

                    currentView.setText(dateToFormDateString(newCalendar.time))

                }, tempYear, tempMonth, tempDay)
                if (maxDate != null) datePicker.datePicker.maxDate = maxDate.time
                if (minDate != null) datePicker.datePicker.minDate = minDate.time
                datePicker.show()
            }
        }

        fun formDateStringToDate(s: String): Date {
            // expects dd/MM/yyyy
            return formDateFormat.parse(s)!!
        }

        fun dateToFormDateString(d: Date): String {
            // returns dd/MM/yyyy
            return formDateFormat.format(d)
        }

        fun getMonthsBetweenDateStrings(earlierDate: String, laterDate: String): Int {
            val date1 = formDateStringToDate(earlierDate)
            val date2 = formDateStringToDate(laterDate)
            val diff: Long = date2.time - date1.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            val months = days / 30
            return months.toInt()
        }

        fun checkIfFirstDateIsBeforeSecondDate(firstDate: String, secondDate: String): Boolean {
            val date1 = formDateStringToDate(firstDate)
            val date2 = formDateStringToDate(secondDate)
            return date1 <= date2
        }
    }
}