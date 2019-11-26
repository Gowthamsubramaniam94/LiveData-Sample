package com.gowtham.livedata.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gowtham.livedata.R
import com.gowtham.livedata.adapter.StudentDetailsAdapter
import com.gowtham.livedata.db.entity.StudentDetailsEntity
import com.gowtham.livedata.viewmodel.StudentDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mViewModel: StudentDetailsViewModel by inject()
    private val mAdapter: StudentDetailsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        mViewModel.getAllStudentDetails()
            .observe(this, Observer<List<StudentDetailsEntity>> { list ->
                list?.let { mAdapter.setStudentDetails(it) }
            })
    }

    private fun setupRecyclerView() {
        student_details_RV.layoutManager = LinearLayoutManager(this)
        student_details_RV.setHasFixedSize(true)
        student_details_RV.adapter = mAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newStudentDetail = StudentDetailsEntity(
                data.getStringExtra(AddStudentDetailsActivity.EXTRA_NAME)!!,
                data.getStringExtra(AddStudentDetailsActivity.EXTRA_GENDER)!!,
                data.getStringExtra(AddStudentDetailsActivity.EXTRA_AGE)!!,
                data.getStringExtra(AddStudentDetailsActivity.EXTRA_COURSE)!!,
                data.getStringExtra(AddStudentDetailsActivity.EXTRA_FEE_PAID)!!
            )
            mViewModel.insert(newStudentDetail)

            Toast.makeText(this, "Record saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Record not saved!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ADD_NOTE_REQUEST = 1
    }

}
