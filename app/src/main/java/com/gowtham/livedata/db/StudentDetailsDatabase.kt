package com.gowtham.livedata.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gowtham.livedata.db.dao.StudentDetailsDAO
import com.gowtham.livedata.db.entity.StudentDetailsEntity

@Database(entities = [StudentDetailsEntity::class], version = 1)
abstract class StudentDetailsDatabase : RoomDatabase() {

    abstract fun studentDetailsDAO(): StudentDetailsDAO

    private var instance: StudentDetailsDatabase? = null

    fun getInstance(context: Context): StudentDetailsDatabase? {

        if (instance == null) {
            synchronized(StudentDetailsDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDetailsDatabase::class.java, "student_details_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
        }
        return instance
    }

    fun destroyInstance() {
        instance = null
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                .execute()
        }
    }

}

class PopulateDbAsyncTask(db: StudentDetailsDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val noteDao = db?.studentDetailsDAO()

    override fun doInBackground(vararg p0: Unit?) {
        noteDao?.insert(
            StudentDetailsEntity(
                "Gowtham",
                "1",
                "25",
                "CSC",
                "1000"
            )
        )
        noteDao?.insert(
            StudentDetailsEntity(
                "Raj",
                "1",
                "25",
                "ECE",
                "1500"
            )
        )

    }
}

