package com.example.studentinfodb.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.studentinfodb.database.StudentDAO
import com.example.studentinfodb.database.StudentInfo

/*
pass the DAO instead of the whole database
 */

class StudentRepository(private val studentDAO: StudentDAO) {

    val allStudents: LiveData<List<StudentInfo>> = studentDAO.loadAllStudents()

    /*
    suspend queries off  the main thread
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread

    suspend fun insert(studentInfo: StudentInfo) {
        studentDAO.insertStudent(studentInfo)
    }

}