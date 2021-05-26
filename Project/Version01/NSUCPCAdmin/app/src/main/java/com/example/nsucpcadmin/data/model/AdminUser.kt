package com.example.nsucpcadmin.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdminUser(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val nsu_id: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val gender: String = "",
    val present_address: String = "",
    val profileCompleted: Int = 0
) : Parcelable
