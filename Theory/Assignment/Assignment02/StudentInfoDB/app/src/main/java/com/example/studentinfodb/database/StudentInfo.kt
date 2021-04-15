package com.example.studentinfodb.database

data class StudentInfo(
    val id: Int,
    val name: String,
    val school: String,
    val department: String,
    val date_of_birth: String,
    val phone_number: Int,
    val nid_number: Int,
    val present_address: String,
    val permanent_address: String
)
