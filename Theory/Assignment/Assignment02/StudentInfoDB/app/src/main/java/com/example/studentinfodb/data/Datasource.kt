package com.example.studentinfodb.data

import com.example.studentinfodb.R
import com.example.studentinfodb.model.Student

class Datasource {
    //  return a list of students
    fun loadStudents(): List<Student> {
        return listOf<Student>(
            Student(R.string.student1, R.drawable.ic_twotone_person_24),
            Student(R.string.student2, R.drawable.ic_twotone_person_24),
            Student(R.string.student3, R.drawable.ic_twotone_person_24),
            Student(R.string.student4, R.drawable.ic_twotone_person_24),
            Student(R.string.student5, R.drawable.ic_twotone_person_24),
            Student(R.string.student6, R.drawable.ic_twotone_person_24),
            Student(R.string.student7, R.drawable.ic_twotone_person_24),
            Student(R.string.student8, R.drawable.ic_twotone_person_24),
            Student(R.string.student9, R.drawable.ic_twotone_person_24),
            Student(R.string.student10, R.drawable.ic_twotone_person_24)
        )
    }
}