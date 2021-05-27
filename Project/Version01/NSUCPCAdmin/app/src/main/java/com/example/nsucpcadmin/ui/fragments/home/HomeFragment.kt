package com.example.nsucpcadmin.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.nsucpcadmin.R
import com.example.nsucpcadmin.databinding.FragmentHomeBinding
import com.example.nsucpcadmin.ui.fragments.BaseFragment

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.postNewJobCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createJobPostFragment)
        }


        // show recycler view with shimmer effect
        binding.recyclerviewJobCard.showShimmer()



        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}