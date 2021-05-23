package com.example.nsucpcadmin.ui.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nsucpcadmin.databinding.ActivitySplashBinding
import com.example.nsucpcadmin.ui.activities.auth.LoginActivity
import com.example.nsucpcadmin.ui.activities.auth.SignupActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root

        // lottie animation
        binding.logoLottieAnimation.playAnimation()

        /*
        * goto [SignupActivity] when [splashSignUpButton] pressed
         */
        binding.splashSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        /*
        * goto [LoginActivity] when [splashLoginLinkText] text pressed
         */
        binding.splashLoginLinkText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        setContentView(view)
    }
}