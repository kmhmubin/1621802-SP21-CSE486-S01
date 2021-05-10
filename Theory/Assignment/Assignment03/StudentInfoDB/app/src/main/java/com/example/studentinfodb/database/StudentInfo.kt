package com.example.studentinfodb.database


data class StudentInfo(

    val studentId: String?,

    var studentName: String? = null,

    var schoolName: String? = null,

    var departmentName: String? = null,

    var dateOfBirth: String? = null,

    var phoneNumber: String? = null,

    var nidNumber: String? = null,

    var presentAddress: String? = null,

    var permanentAddress: String? = null
)
