package com.example.nsucpcadmin.ui.fragments.mail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nsucpcadmin.databinding.FragmentMailBinding


class MailFragment : Fragment() {

    private lateinit var _binding: FragmentMailBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMailBinding.inflate(inflater, container, false)
        val view = binding.root

        // show the mail recycler view with shimmer effect
        binding.recyclerviewMailCard.showShimmer()


        return view
    }


}