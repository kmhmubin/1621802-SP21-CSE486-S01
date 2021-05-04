package com.example.studentinfofbdb.ui.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.studentinfofbdb.R
import com.example.studentinfofbdb.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
                // Navigate to the list Fragment
                Navigation.findNavController(view)
                    .navigate(R.id.action_loginFragment_to_listFragment)

            }
        }

        // Clear the error once more than 8 characters are typed.
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
        return text != null && text.length >= 8
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}