package com.example.nsucpcadmin.data.model

data class AdminUser(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: String = "",
    val gender: String = "",
    val profileComplete: Int = 0
)
