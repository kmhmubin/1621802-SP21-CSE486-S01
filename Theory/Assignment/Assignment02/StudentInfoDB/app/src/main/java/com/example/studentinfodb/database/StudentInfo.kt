package com.example.studentinfodb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student_db")
data class StudentInfo(
    @PrimaryKey
    val studentId: String,
    @ColumnInfo(name = "student_name")
    var studentName: String?,
    @ColumnInfo(name = "school_name")
    var schoolName: String?,
    @ColumnInfo(name = "department_name")
    var departmentName: String?,
    @ColumnInfo(name = "date_of_birth")
    var dateOfBirth: String?,
    @ColumnInfo(name = "phone_number")
    var phoneNumber: String?,
    @ColumnInfo(name = "nid_number")
    var nidNumber: String?,
    @ColumnInfo(name = "present_address")
    var presentAddress: String?,
    @ColumnInfo(name = "permanent_address")
    var permanentAddress: String?
)
