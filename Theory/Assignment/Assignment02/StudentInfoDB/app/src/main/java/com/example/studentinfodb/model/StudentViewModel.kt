package com.example.studentinfodb.model

import android.app.Application
import androidx.lifecycle.*
import com.example.studentinfodb.database.StudentDatabase
import com.example.studentinfodb.database.StudentInfo
import com.example.studentinfodb.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val allStudents: LiveData<List<StudentInfo>>
    private val repository: StudentRepository

    init {
        val studentDAO = StudentDatabase.getDatabase(application).studentDAO()
        repository = StudentRepository(studentDAO)
        allStudents = repository.allStudents
    }

    fun insert(studentInfo: StudentInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(studentInfo)
        }
    }

}


