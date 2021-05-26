package com.example.nsucpcadmin.ui.activities.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nsucpcadmin.databinding.ActivityForgetPasswordBinding
import com.example.nsucpcadmin.ui.activities.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ForgetPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        /*
        * When submit button pressed
         */
        binding.forgetPasswordButton.setOnClickListener {
            val email: String = binding.forgetPasswordEmailEditText.text.toString().trim()

            // check email is empty or not
            if (email.isEmpty()) {
                binding.forgetPasswordEmailTextLayout.error = "Please enter an email"
            } else {
                // show the progerss bar
                showProgressBar()

                // send a reset link to the user email
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        // hide progress bar
                        hideProgressBar()

                        if (task.isSuccessful) {
                            Toast.makeText(this, "Reset email sent successful", Toast.LENGTH_LONG)
                                .show()
                            finish()
                        } else {
                            showSnackBar(task.exception!!.message.toString(), true)
                            FirebaseCrashlytics.getInstance().setCustomKey(
                                "forget password",
                                task.exception!!.message.toString()
                            )
                            FirebaseCrashlytics.getInstance().recordException(task.exception!!)
                        }
                    }
            }
        }


        setContentView(view)
    }
}