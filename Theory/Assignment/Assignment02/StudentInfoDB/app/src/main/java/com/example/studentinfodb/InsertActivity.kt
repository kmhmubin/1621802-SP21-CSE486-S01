package com.example.studentinfodb

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studentinfodb.databinding.ActivityInsertBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


class InsertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert)


        // academic school list items
        val schoolNames = listOf(
            "School of Business & Economics",
            "School of Humanities & Social " +
                    "Sciences",
            "School of Engineering & Physical Sciences",
            "School of Health & Life Sciences"
        )

        val schoolAdapter = ArrayAdapter(this, R.layout.school_list, schoolNames)
        (binding.schoolListDropdown.editText as? AutoCompleteTextView)?.setAdapter(schoolAdapter)

        // department name list
        val departmentNames = listOf(
            "Accounting & Finance",
            "Economics",
            "Management",
            "Marketing & International Business",
            "MBA & EMBA Programs"
        )

        val departmentAdapter = ArrayAdapter(this, R.layout.school_list, departmentNames)
        (binding.departmentListDropdown.editText as? AutoCompleteTextView)?.setAdapter(
            departmentAdapter
        )


        binding.datePickerButton.setOnClickListener {
            dobDatePicker(it)
        }


    }

    private fun dobDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

//         open the dialog
        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/ ${selectedMonth + 1}/ $selectedYear"
                // show the date in the text view
                binding.selectedDate.text = selectedDate
            },
            year,
            month,
            day
        )
        // restriction for the future date selection
        datePicker.datePicker.maxDate = Date().time - 86400000
        // visible the date picker dialog
        datePicker.show()
        
    }


}