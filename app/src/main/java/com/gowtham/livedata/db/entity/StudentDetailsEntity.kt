package com.gowtham.livedata.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_details")
data class StudentDetailsEntity(

    var Name: String,
    var Gender: String,
    var Age: String,
    var Course: String,
    var FeePaid: String
) {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0
}

