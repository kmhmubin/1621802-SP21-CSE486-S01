package com.example.studentinfodb.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDAO {

    /*
    Insert student data, if already exist then ignore
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudent(vararg studentInfo: StudentInfo)

    /*
    Read all the date from database and show the list in ascending order
     */
    @Query("SELECT * FROM student_db ORDER BY studentId ASC")
    fun loadAllStudents(): LiveData<List<StudentInfo>>
}