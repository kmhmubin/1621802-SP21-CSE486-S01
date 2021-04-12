package com.example.studentinfodb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentinfodb.databinding.ActivityInsertStudentBinding
import com.google.android.material.textfield.TextInputLayout

class InsertStudentActivity : AppCompatActivity() {
    private lateinit var textField: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding the layout
        val binding = ActivityInsertStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
//        val adapter = ArrayAdapter(requireContext(), R.layout.academic_school_list, items)
//        (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}