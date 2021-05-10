package com.example.studentinfodb.database


data class StudentInfo(

    val studentId: Int,

    var studentName: String?,

    var schoolName: String?,

    var departmentName: String?,

    var dateOfBirth: String?,

    var phoneNumber: String?,

    var nidNumber: String?,

    var presentAddress: String?,

    var permanentAddress: String?
)
