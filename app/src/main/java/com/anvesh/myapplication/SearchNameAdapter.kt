package com.anvesh.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchNameAdapter(val namesList: ArrayList<String>) :
    RecyclerView.Adapter<SearchNameAdapter.SearchNameViewHolder>() {
    class SearchNameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_name_row, parent, false)

        return SearchNameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return namesList.size
    }

    override fun onBindViewHolder(holder: SearchNameViewHolder, position: Int) {
        val name = namesList[position]
        holder.txtName.text = name

    }
}