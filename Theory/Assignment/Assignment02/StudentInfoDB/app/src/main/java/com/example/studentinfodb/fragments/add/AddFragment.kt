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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root


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
        (binding.schoolListDropdown as? AutoCompleteTextView)?.setAdapter(schoolAdapter)


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
        (binding.departmentListDropdown as? AutoCompleteTextView)?.setAdapter(
            departmentAdapter
        )

        /*
        Date picker for date of birth
         */

        binding.datePickerButton.setOnClickListener {
            dobDatePicker(it)
        }


        /*
     * view model object for [StudentViewModel]
      */
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        /*
        Grab info when submit button pressed
         */
        binding.submitButton.setOnClickListener {
            insertDataToDatabase()
        }



        return view
    }

    private fun insertDataToDatabase() {
        val studentId = binding.nsuId.toString()
        val studentName = binding.studentName.toString()
        val schoolName = binding.schoolListDropdown.toString()
        val departmentName = binding.departmentListDropdown.toString()
        val dateOfBirth = binding.dobDate.toString()
        val phoneNumber = binding.phoneNumber.toString()
        val nidNumber = binding.nidNumber.toString()
        val presentAddress = binding.presentAddress.toString()
        val permanentAddress = binding.permanentAddress.toString()

        val student = StudentInfo(
            studentId,
            studentName,
            schoolName,
            departmentName,
            dateOfBirth,
            phoneNumber,
            nidNumber,
            presentAddress,
            permanentAddress
        )
        mStudentViewModel.insert(student)

        Snackbar.make(
            requireContext(), binding.addLayout, "Successfully Added", Snackbar
                .LENGTH_LONG
        ).show()
        // navigate back to main page
        findNavController().navigate(R.id.action_addFragment_to_listFragment)

//        if (inputCheck(
//                studentId, studentName, schoolName, departmentName, dateOfBirth, phoneNumber,
//                nidNumber, presentAddress, permanentAddress
//            )
//        ) {
//            // create student object
//            val student = StudentInfo(
////                Integer.parseInt(studentId.toString()),
//                studentId,
//                studentName,
//                schoolName,
//                departmentName,
//                dateOfBirth,
//                phoneNumber,
//                nidNumber,
//                presentAddress,
//                permanentAddress
//            )
//
//            // add data to database
//            mStudentViewModel.insert(student)
//            // show a success message in snack bar
//            Snackbar.make(
//                requireContext(), binding.addLayout, "Successfully Added", Snackbar
//                    .LENGTH_LONG
//            ).show()
//            // navigate back to main page
//            findNavController().navigate(R.id.action_addFragment_to_listFragment)
//        } else {
//            // show a error message in snack bar
//            Snackbar.make(
//                requireContext(), binding.addLayout, "Please fill out all fields", Snackbar
//                    .LENGTH_LONG
//            ).show()
//        }
    }

    /*
    Check the input field is empty or not
     */
//    private fun inputCheck(
//        studentId: Int, studentName: String, schoolName: String,
//        departmentName: String, dateOfBirth: String, phoneNumber: String,
//        nidNumber: String, presentAddress: String, permanentAddress: String
//    ): Boolean {
//        return !(TextUtils.isEmpty(studentId.toString()) && TextUtils.isEmpty(studentName) && TextUtils
//            .isEmpty(schoolName) && TextUtils.isEmpty(departmentName) && TextUtils.isEmpty(
//            dateOfBirth
//        )
//                && TextUtils.isEmpty(phoneNumber.toString()) && TextUtils.isEmpty(nidNumber.toString())
//                && TextUtils.isEmpty(presentAddress) && TextUtils.isEmpty(permanentAddress))
//    }

    /*
    Function for date picker dialog
    features:
    1. Default show today's date
    2. Can't select future date
    3. show the date inside of edit text view
     */
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

                // show the date in the edit text view
                binding.dobDate.setText(
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


//    var idString: String = studentIDEditText.getText().toString()
//    var id = Integer.valueOf(idString)
}





