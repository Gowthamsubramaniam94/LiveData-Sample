package com.gowtham.livedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gowtham.livedata.db.entity.StudentDetailsEntity
import com.gowtham.livedata.repository.StudentDetailsRepository

class StudentDetailsViewModel(private var repository: StudentDetailsRepository) : ViewModel() {

    private var allNotes: LiveData<List<StudentDetailsEntity>> = repository.getAllStudents()

    fun insert(note: StudentDetailsEntity) {
        repository.insert(note)
    }

    fun deleteAllStudentDetails() {
        repository.deleteAllStudents()
    }

    fun getAllStudentDetails(): LiveData<List<StudentDetailsEntity>> {
        return allNotes
    }
}