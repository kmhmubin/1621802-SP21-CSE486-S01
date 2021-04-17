package com.example.studentinfodb.fragments.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.studentinfodb.R
import com.example.studentinfodb.databinding.FragmentAddBinding
import com.example.studentinfodb.model.StudentViewModel
import java.util.*


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

//    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        /*
        * view model object for [StudentViewModel]
         */
//        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)


        /*
        Academic school dropdown lists with array adapter
         */

        val schoolNames = listOf(
            "School of Business & Economics",
            "School of Humanities & Social " +
                    "Sciences",
            "School of Engineering & Physical Sciences",
            "School of Health & Life Sciences"
        )

        val schoolAdapter = ArrayAdapter(requireContext(), R.layout.school_list, schoolNames)
        (binding.schoolListDropdown.editText as? AutoCompleteTextView)?.setAdapter(schoolAdapter)


        /*
       Department names dropdown lists with array adapter
        */

        val departmentNames = listOf(
            "Accounting & Finance",
            "Economics",
            "Management",
            "Marketing & International Business",
            "MBA & EMBA Programs"
        )

        val departmentAdapter =
            ArrayAdapter(requireContext(), R.layout.school_list, departmentNames)
        (binding.departmentListDropdown.editText as? AutoCompleteTextView)?.setAdapter(
            departmentAdapter
        )

        /*
        Date picker for date of birth
         */

        binding.datePickerButton.setOnClickListener {
            dobDatePicker(it)
        }

        /*
        submit button
         */
//        binding.submitButton.setOnClickListener {
//            insertDataToDatabase()
//        }

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun dobDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

//         open the dialog
        val datePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth,
                                                 selectedDayOfMonth ->
//                val selectedDate = "$selectedDayOfMonth/ ${selectedMonth + 1}/ $selectedYear"
                // show the date in the text view
                binding.dobDate.editText?.setText(
                    "$selectedDayOfMonth/ ${selectedMonth + 1}/ " +
                            "$selectedYear"
                ).toString()
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

//    private fun insertDataToDatabase() {
//
//    }
}

