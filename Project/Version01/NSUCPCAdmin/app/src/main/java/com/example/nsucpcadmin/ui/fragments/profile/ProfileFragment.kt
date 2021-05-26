package com.example.nsucpcadmin.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nsucpcadmin.databinding.FragmentProfileBinding
import com.example.nsucpcadmin.ui.activities.editprofile.EditProfileActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        // edit profile button
        binding.profileEditButton.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }



        return view
    }


}