package com.example.studentinfodb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinfodb.R
import com.example.studentinfodb.model.Student
import org.w3c.dom.Text

class ItemAdapter(private val context: Context, private val dataset: List<Student>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // create a view holder
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        create a new view
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        replace the contents of a view by data
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
//        return the size of dataset
        return dataset.size
    }

}