package com.example.nsucpcadmin.ui.activities.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nsucpcadmin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        /*
        * goto [forget password] activity when forget text pressed
         */

        binding.signInForgetPasswordTextLink.setOnClickListener {
            startActivity(Intent(this, ForgetPasswordActivity::class.java))
        }

        /*
        * goto signup activity when signup text pressed
         */
        binding.signUpLinkText.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }


        setContentView(view)
    }
}