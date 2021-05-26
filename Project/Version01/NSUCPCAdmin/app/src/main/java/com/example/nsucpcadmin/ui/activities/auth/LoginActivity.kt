package com.example.nsucpcadmin.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.nsucpcadmin.MainActivity
import com.example.nsucpcadmin.data.firebase.FirebaseSource
import com.example.nsucpcadmin.data.model.AdminUser
import com.example.nsucpcadmin.databinding.ActivityLoginBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.example.nsucpcadmin.ui.activities.editprofile.EditProfileActivity
import com.example.nsucpcadmin.utils.Constants
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

                    if (task.isSuccessful) {
                        FirebaseSource().getUserDetails(this)
                    } else {
                        hideProgressBar()
                        showSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

    /*
    * function to notify user that logged is success and get user details from Firestore database after auth
     */

    fun userLoggedInSuccess(user: AdminUser) {
        // hide the progress bar
        hideProgressBar()


        if (user.profileComplete == 0) {
            // if the user profile incomplete
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        } else {
            //  goto main activity
            startActivity(Intent(this, MainActivity::class.java))
        }

        finish()
    }


}
