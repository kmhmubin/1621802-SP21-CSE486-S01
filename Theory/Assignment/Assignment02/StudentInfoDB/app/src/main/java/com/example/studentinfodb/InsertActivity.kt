package com.example.studentinfodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import com.example.studentinfodb.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert)


        // academic school list items
        val items = listOf(
            "School of Business & Economics",
            "School of Humanities & Social " +
                    "Sciences",
            "School of Engineering & Physical Sciences",
            "School of Health & Life Sciences"
        )

        val adapter = ArrayAdapter(this, R.layout.school_list, items)
        (binding.schoolListDropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)


    }
}