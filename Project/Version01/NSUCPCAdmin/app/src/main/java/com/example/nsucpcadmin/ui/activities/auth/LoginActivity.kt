package com.example.nsucpcadmin.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.nsucpcadmin.R
import com.example.nsucpcadmin.databinding.ActivityLoginBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        /*
        * goto forget password activity when forget text pressed
         */
        binding.signInForgetPasswordTextLink.setOnClickListener(this)
        // click event for login button
        binding.signInButton.setOnClickListener(this)

        /*
        * goto signup activity when signup text pressed
         */
        binding.signUpLinkText.setOnClickListener(this)


        setContentView(view)
    }

    /*
    * All clickable components
     */

    override fun onClick(view: View?) {
        if (view != null) {
            when (view) {
                // forget password text link
                binding.signInForgetPasswordTextLink -> {
                    startActivity(Intent(this, ForgetPasswordActivity::class.java))
                }
                // login button
                binding.signInButton -> {
                    logInUser()
                }
                // sign up text link
                binding.signUpLinkText -> {
                    startActivity(Intent(this, SignupActivity::class.java))
                }

            }
        }
    }

    /*
    * Login validation form
     */

    private fun loginValidation(): Boolean {
        return when {
            binding.signInUserEmailEditText.text.toString().trim().isEmpty() -> {
                binding.signInUserEmailTextLayout.error = "Please enter your email"
                false
            }
            binding.signInUserPasswordEditText.text.toString().trim().isEmpty() -> {
                binding.signInUserPasswordTextLayout.error = "Please enter your email"
                false
            }
            else -> {
                true
            }
        }
    }

    /*
    * function for login the user
     */

    private fun logInUser() {
        if (loginValidation()) {
            // show the progress bar
            showProgressBar()

            val email: String = binding.signInUserEmailEditText.text.toString().trim()
            val password: String = binding.signInUserPasswordEditText.text.toString().trim()

            // login using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    //hide the progress bar
                    hideProgressBar()

                    if (task.isSuccessful) {
                        showSnackBar("Login Successful", false)
                    } else {
                        showSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }
}