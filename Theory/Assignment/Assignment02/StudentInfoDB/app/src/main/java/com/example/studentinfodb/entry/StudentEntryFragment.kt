package com.example.studentinfodb.entry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentinfodb.R


/**
 * A simple [Fragment] subclass.
 * Use the [StudentEntryFragment] factory method to
 * create an instance of this fragment.
 */
class StudentEntryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_entry, container, false)
    }


}