package com.example.studentinfodb.fragments.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.studentinfodb.R
import com.example.studentinfodb.database.StudentInfo
import com.example.studentinfodb.databinding.FragmentAddBinding
import com.example.studentinfodb.model.StudentViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        // school list spinner
        val schoolList = binding.schoolSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.school_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            schoolList.adapter = adapter
        }
//        val selectedSchool = schoolList.onItemSelectedListener.toString()

        // department list spinner
        val departmentList = binding.departmentSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.department_list,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            departmentList.adapter = arrayAdapter
        }
//        val selectedDepartment = departmentList.onItemSelectedListener.toString()

        // date picker for date of birth
        binding.datePickerButton.setOnClickListener {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)

            // open the dialog
            val datePicker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    binding.dobText.setText("$selectedDayOfMonth/ ${selectedMonth + 1}/ $selectedYear")
                        .toString()
                },
                year,
                month,
                day
            )
            datePicker.datePicker.maxDate = Date().time - 86400000
            datePicker.show()
        }

        // view model object for student view model
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        // save info when submit button pressed
        binding.submitButton.setOnClickListener {
//            insertDataToDatabase()
            val studentId = binding.nsuId.text.toString().toInt()
            val studentName = binding.studentName.text.toString()
            val schoolName = binding.schoolSpinner.onItemSelectedListener.toString()
            val departmentName = binding.departmentSpinner.onItemSelectedListener.toString()
            val dateOfBirth = binding.dobText.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            val nidNumber = binding.nidNumber.text.toString()
            val presentAddress = binding.presentAddress.text.toString()
            val permanentAddress = binding.permanentAddress.text.toString()

            val student = StudentInfo(
                studentId, studentName, schoolName, departmentName,
                dateOfBirth, phoneNumber, nidNumber, presentAddress, permanentAddress
            )
            mStudentViewModel.insert(student)

            Snackbar.make(
                requireContext(), binding.addLayout, "Successfully Added", Snackbar
                    .LENGTH_LONG
            ).show()
            // navigate to main page
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}