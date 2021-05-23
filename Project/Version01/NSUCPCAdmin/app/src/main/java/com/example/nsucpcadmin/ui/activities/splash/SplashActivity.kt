package com.example.nsucpcadmin.ui.activities.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nsucpcadmin.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root

        // lottie animation
        binding.logoLottieAnimation.playAnimation()



        setContentView(view)
    }
}