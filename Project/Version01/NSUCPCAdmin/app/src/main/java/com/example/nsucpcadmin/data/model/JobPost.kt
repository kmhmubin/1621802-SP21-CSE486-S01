package com.example.nsucpcadmin.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobPost(
    val id: String = "",
    val company_name: String = "",
    val position_name: String = "",
    val job_type: String = "",
    val salary: Double = 0.0,
    val job_description: String = "",
    val application_date: String = "",
    val recruiter_mail: String = "",
    val company_logo: String = ""

) : Parcelable
