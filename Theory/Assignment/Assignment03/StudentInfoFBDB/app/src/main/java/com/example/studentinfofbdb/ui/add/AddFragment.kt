package com.example.studentinfofbdb.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.studentinfofbdb.R
import com.example.studentinfofbdb.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        /*
        * Get the string array for school names using array adapter
         */

        val schoolNameString = resources.getStringArray(R.array.school_list)
        val schoolArrayAdapter = ArrayAdapter(
            requireContext(), R.layout.list_item,
            schoolNameString
        )
        binding.academySchoolAutoCompleteTextView.setAdapter(schoolArrayAdapter)




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}