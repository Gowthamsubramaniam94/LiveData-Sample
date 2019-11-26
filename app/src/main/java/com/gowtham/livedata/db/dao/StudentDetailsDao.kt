package com.gowtham.livedata.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gowtham.livedata.db.entity.StudentDetailsEntity

@Dao
interface StudentDetailsDAO {

    @Insert
    fun insert(note: StudentDetailsEntity)

    @Query("DELETE FROM student_details")
    fun deleteAllStudents()

    @Query("SELECT * FROM student_details ")
    fun getAllStudents(): LiveData<List<StudentDetailsEntity>>

}