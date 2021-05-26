package com.example.nsucpcadmin.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nsucpcadmin.databinding.ActivitySignupBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : BaseActivity() {

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root

        /*
        * goto login activity when login text pressed
         */
        binding.signupLoginLinkText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        /*
        * Register button click function
         */
        binding.signupButton.setOnClickListener {
            registerUser()
        }

        setContentView(view)
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            // name
            binding.signupUserNameEditText.text.toString().trim().isEmpty() -> {
                binding.signupUserNameTextLayout.error = "Please enter your name"
                false
            }
            //email
            binding.signupUserEmailEditText.text.toString().trim().isEmpty() -> {
                binding.signupUserEmailTextLayout.error = "Please enter valid North South email"
                false
            }
            // id
            binding.signupUserIDEditText.text.toString().trim().isEmpty() -> {
                binding.signupUserIDTextLayout.error = "Please enter your 10 digit NSU ID"
                false
            }
            // password
            binding.signupUserPasswordEditText.text.toString().trim().isEmpty() -> {
                binding.signupUserPasswordTextLayout.error = "Please enter password"
                false
            }
            binding.signupUserPasswordEditText.text.toString().length < 4 -> {
                binding.signupUserPasswordTextLayout.error =
                    "Password must contain at least 5 characters"
                false
            }
            // confirm password
            binding.signupUserConfirmPasswordEditText.text.toString().trim().isEmpty() -> {
                binding.signupUserConfirmPasswordTextLayout.error =
                    "Please enter your password again"
                false
            }
            binding.signupUserConfirmPasswordEditText.text.toString() != binding.signupUserPasswordEditText.text.toString() -> {
                binding.signupUserConfirmPasswordTextLayout.error =
                    "Password & confirm password does not match"
                false
            }
            else -> {
                true
            }

        }
    }


    /*
    * function to register the user with email and password using FirebaseAuth
     */

    private fun registerUser() {
        // check the validation if the entries are valid or not
        if (validateRegisterDetails()) {
            // show the progress dialog
            showProgressBar()

            val email: String = binding.signupUserEmailEditText.text.toString().trim()
            val password: String = binding.signupUserPasswordEditText.text.toString().trim()

            // create an instance and register user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        // hide the progress bar
                        hideProgressBar()

                        // if register is successful
                        if (task.isSuccessful) {
                            // Firebase register user
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            // show the registration success message
                            showSnackBar("Registration Successful", false)
                            // after registration complete then logout and send to login screen
                            FirebaseAuth.getInstance().signOut()
                            // finish the registration screen
                            finish()
                        } else {
                            // show error message
                            showSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
                )
        }
    }


}