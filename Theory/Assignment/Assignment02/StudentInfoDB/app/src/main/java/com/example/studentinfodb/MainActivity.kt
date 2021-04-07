package com.example.studentinfodb

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinfodb.adapter.ItemAdapter
import com.example.studentinfodb.data.Datasource
import com.example.studentinfodb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change into view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // initialize the data source
        val myDataSource = Datasource().loadStudents()

        // add reference for the recycle view
        recyclerView = binding.recyclerView
        recyclerView.adapter = ItemAdapter(this, myDataSource)
        // improve performance for fixed dataset
        recyclerView.setHasFixedSize(true)


    }

    fun fabButton(view: View) {
        val intent = Intent(applicationContext, InsertStudentActivity::class.java)
        startActivity(intent)
    }
}