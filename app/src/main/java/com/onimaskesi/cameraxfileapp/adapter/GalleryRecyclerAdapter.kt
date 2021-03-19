package com.onimaskesi.cameraxfileapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onimaskesi.cameraxfileapp.DisplayImageActivity
import com.onimaskesi.cameraxfileapp.R
import com.onimaskesi.cameraxfileapp.model.ImageObj
import kotlinx.android.synthetic.main.gallery_recycler_raw.view.*


class GalleryRecyclerAdapter(val context : Context, var imageList : ArrayList<ImageObj>) : RecyclerView.Adapter<GalleryRecyclerAdapter.FileHolder>() {


    class FileHolder(itemview : View) : RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.gallery_recycler_raw , parent,false)
        return FileHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }


    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        var path = imageList.get(position).path

        val options = BitmapFactory.Options()
        //options.inJustDecodeBounds = true
        //options.inSampleSize = calculateInSampleSize(options, 500, 500)

        val resized = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path),200,200)

        //var bitmap = BitmapFactory.decodeFile(path, options)
        holder.itemView.imageViewGallery.setImageBitmap(resized)

        holder.itemView.imageViewGallery.setOnClickListener{
            var path = imageList.get(position).path
            var intent = Intent(context, DisplayImageActivity::class.java)
            intent.putExtra("path", path)
            context.startActivity(intent)
            (context as Activity).finish()

        }

    }


}