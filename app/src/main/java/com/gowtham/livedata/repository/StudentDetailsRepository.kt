package com.gowtham.livedata.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.gowtham.livedata.db.dao.StudentDetailsDAO
import com.gowtham.livedata.db.entity.StudentDetailsEntity

class StudentDetailsRepository(private val aStudentDetailsDAO: StudentDetailsDAO) {

    private val allNotes: LiveData<List<StudentDetailsEntity>> = aStudentDetailsDAO.getAllStudents()

    fun insert(note: StudentDetailsEntity) {
        InsertNoteAsyncTask(
            aStudentDetailsDAO
        ).execute(note)
    }

    fun deleteAllStudents() {
        DeleteAllNotesAsyncTask(
            aStudentDetailsDAO
        ).execute()
    }

    fun getAllStudents(): LiveData<List<StudentDetailsEntity>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(val aStudentDetailsDAO: StudentDetailsDAO) :
        AsyncTask<StudentDetailsEntity, Unit, Unit>() {

        override fun doInBackground(vararg note: StudentDetailsEntity?) {
            aStudentDetailsDAO.insert(note[0]!!)
        }
    }


    private class DeleteAllNotesAsyncTask(val aStudentDetailsDAO: StudentDetailsDAO) :
        AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            aStudentDetailsDAO.deleteAllStudents()
        }
    }

}