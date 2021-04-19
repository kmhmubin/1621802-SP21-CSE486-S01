package com.example.studentinfodb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinfodb.R
import com.example.studentinfodb.database.StudentInfo

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var studentList = emptyList<StudentInfo>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.student_id_list)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // show the date in the view holder
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.student_list,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.textView.text = currentItem.studentId.toString()
    }

    override fun getItemCount(): Int {
        // return the size of the list
        return studentList.size
    }

    fun setDate(studentInfo: List<StudentInfo>) {
        this.studentList = studentInfo
        notifyDataSetChanged()
    }
}