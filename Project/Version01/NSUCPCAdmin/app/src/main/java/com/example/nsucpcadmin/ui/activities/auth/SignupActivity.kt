package com.example.nsucpcadmin.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nsucpcadmin.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

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

        setContentView(view)
    }
}