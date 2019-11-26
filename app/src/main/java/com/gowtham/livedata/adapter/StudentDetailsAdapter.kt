package com.gowtham.livedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gowtham.livedata.R
import com.gowtham.livedata.db.entity.StudentDetailsEntity

class StudentDetailsAdapter : RecyclerView.Adapter<StudentDetailsAdapter.ViewHolder>() {
    private var mStudentDetails: List<StudentDetailsEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_inflate_student_details, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aDetails = mStudentDetails[position]
        holder.mNameTXT.text = aDetails.Name
        holder.mCourseTXT.text = aDetails.Course
        holder.mFeePaidTXT.text = aDetails.FeePaid
    }

    override fun getItemCount(): Int {
        return mStudentDetails.size
    }

    fun setStudentDetails(notes: List<StudentDetailsEntity>) {
        this.mStudentDetails = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mNameTXT: TextView = itemView.findViewById(R.id.student_name_TXT)
        var mCourseTXT: TextView = itemView.findViewById(R.id.student_course_TXT)
        var mFeePaidTXT: TextView = itemView.findViewById(R.id.student_fee_paid_TXT)

    }
}