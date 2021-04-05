package com.example.studentinfodb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinfodb.adapter.ItemAdapter
import com.example.studentinfodb.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // initialize the data source
        val myDataSource = Datasource().loadStudents()

        // add reference for the recycle view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataSource)
        // improve performance for fixed dataset
        recyclerView.setHasFixedSize(true)

    }
}