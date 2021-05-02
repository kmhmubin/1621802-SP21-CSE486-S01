package com.example.studentinfofbdb.fragments.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studentinfofbdb.NavigationHost
import com.example.studentinfofbdb.R
import com.example.studentinfofbdb.databinding.FragmentLoginBinding
import com.example.studentinfofbdb.fragments.list.ListFragment


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set an error if the password is less than 8 characters.
        binding.loginButton.setOnClickListener {
            if (!isPasswordValid(binding.passwordEditText.text!!)) {
                binding.passwordTextInput.error = getString(R.string.error_password)
            } else {
                // clear the error
                binding.passwordTextInput.error = null
                // navigate to next fragment
                (activity as NavigationHost).navigateTo(ListFragment(), false)
            }
        }

        // clear the error once more than 8 characters are typed
        binding.passwordEditText.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(binding.passwordEditText.text!!)) {
                // clear the error
                binding.passwordTextInput.error = null
            }
            false
        }

        return view
    }

    private fun isPasswordValid(text: Editable): Boolean {
        return text.length >= 8
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}