package com.onimaskesi.cameraxfileapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.onimaskesi.cameraxfileapp.R
import com.onimaskesi.cameraxfileapp.model.entities.FileObj
import kotlinx.android.synthetic.main.recycler_raw.view.*

class FileRecyclerAdapter(val fileList : ArrayList<FileObj>, val fileButton : Button, val fileListRV : RecyclerView) : RecyclerView.Adapter<FileRecyclerAdapter.FileHolder>() {


    class FileHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.recycler_raw,parent,false)
        return FileHolder(view)
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        holder.itemView.fileRecyclerElementBtn.text = fileList[position].name
        holder.itemView.fileRecyclerElementBtn.setOnClickListener{

            fileButton.text = fileList[position].name
            fileListRV.visibility = View.INVISIBLE
        }
    }
}