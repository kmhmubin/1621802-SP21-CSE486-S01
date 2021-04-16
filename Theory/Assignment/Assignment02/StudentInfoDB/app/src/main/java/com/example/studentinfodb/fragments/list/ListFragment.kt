package com.example.studentinfodb.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentinfodb.R
import com.example.studentinfodb.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        // Inflate the layout for this fragment

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        /*
        navigate to [Add Fragment] Page
         */
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }


        return view


    }


}