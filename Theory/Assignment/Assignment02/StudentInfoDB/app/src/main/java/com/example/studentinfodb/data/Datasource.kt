package com.example.studentinfodb.data

import com.example.studentinfodb.R
import com.example.studentinfodb.model.Student

class Datasource {
//  return a list of students
    fun loadStudents():List<Student>{
        return listOf<Student>(
            Student(R.string.student1),
            Student(R.string.student2),
            Student(R.string.student3),
            Student(R.string.student4),
            Student(R.string.student5),
            Student(R.string.student6),
            Student(R.string.student7),
            Student(R.string.student8),
            Student(R.string.student9),
            Student(R.string.student10),
        )
    }
}