package com.gowtham.livedata.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gowtham.livedata.R
import kotlinx.android.synthetic.main.activity_add_student_details.*

class AddStudentDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "com.gowtham.livedata.EXTRA_NAME"
        const val EXTRA_GENDER = "com.gowtham.livedata.EXTRA_GENDER"
        const val EXTRA_AGE = "com.gowtham.livedata.EXTRA_AGE"
        const val EXTRA_COURSE = "com.gowtham.livedata.EXTRA_COURSE"
        const val EXTRA_FEE_PAID = "com.gowtham.livedata.EXTRA_FEE_PAID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student_details)

        clickListener()
    }


    fun clickListener() {
        student_details_save_BTN.setOnClickListener(View.OnClickListener { saveStudentData() })
    }


    private fun saveStudentData() {
        if (student_name_EDT.text.toString().trim().isBlank() || student_gender_EDT.text.toString().trim().isBlank()
            || student_age_EDT.text.toString().trim().isBlank() || student_course_EDT.text.toString().trim().isBlank()
            || student_fee_paid_EDT.text.toString().trim().isBlank()
        ) {
            Toast.makeText(this, "Please fill all the data!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_NAME, student_name_EDT.text.toString())
            putExtra(EXTRA_GENDER, student_gender_EDT.text.toString())
            putExtra(EXTRA_AGE, student_age_EDT.text.toString())
            putExtra(EXTRA_COURSE, student_course_EDT.text.toString())
            putExtra(EXTRA_FEE_PAID, student_fee_paid_EDT.text.toString())
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}