package com.onimaskesi.cameraxfileapp

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.onimaskesi.cameraxfileapp.adapter.FileRecyclerAdapter
import com.onimaskesi.cameraxfileapp.adapter.GalleryRecyclerAdapter
import com.onimaskesi.cameraxfileapp.model.AppDatabase
import com.onimaskesi.cameraxfileapp.model.ImageObj
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    lateinit var fileName : String

    lateinit var db : AppDatabase

    lateinit var imagesList : ArrayList<ImageObj>
    lateinit var galleryRecyclerViewAdapter : GalleryRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        fileName = intent.getStringExtra("fileName")!!
        fileNameGalleryTV.text = fileName

        db = Room.databaseBuilder( applicationContext,
            AppDatabase::class.java,
            "AppDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        imagesList = ArrayList(db.imageDao().getImagesFromFile(fileName))

        //Recycler View
        imagesRV.layoutManager = GridLayoutManager(this, 2)
        galleryRecyclerViewAdapter = GalleryRecyclerAdapter(this , imagesList)
        imagesRV.adapter = galleryRecyclerViewAdapter
        //imagesRV.setNestedScrollingEnabled(false)
        //imagesRV.setHasFixedSize(true)
        //imagesRV.setItemViewCacheSize(10)

    }
}