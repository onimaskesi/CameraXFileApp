package com.onimaskesi.cameraxfileapp

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.room.Room
import com.onimaskesi.cameraxfileapp.model.AppDatabase
import com.onimaskesi.cameraxfileapp.model.ImageObj
import kotlinx.android.synthetic.main.activity_gallery.*

class GalleryActivity : AppCompatActivity() {

    lateinit var fileName : String

    lateinit var db : AppDatabase

    lateinit var imagesList : List<ImageObj>

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

        imagesList = db.imageDao().getImagesFromFile(fileName)

        //
        imagesGV.adapter = ImageAdapter(this)
        imagesGV.setOnItemClickListener { adapterView, view, i, l ->
            var path = imagesList.get(i).path
            var intent = Intent(applicationContext, DisplayImageActivity::class.java)
            intent.putExtra("path", path)
            startActivity(intent)
        }


    }

    inner class ImageAdapter : BaseAdapter {

        lateinit var context : Context
        constructor(context : Context){
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var iv = ImageView(context)
            var path = imagesList.get(p0).path//name of the image
            var bitmap = BitmapFactory.decodeFile(path)
            iv.setImageBitmap(bitmap)
            iv.layoutParams = AbsListView.LayoutParams(300,300)
            return iv
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return imagesList.size
        }

    }
}