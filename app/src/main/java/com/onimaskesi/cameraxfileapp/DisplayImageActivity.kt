package com.onimaskesi.cameraxfileapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Room
import com.onimaskesi.cameraxfileapp.model.AppDatabase
import com.onimaskesi.cameraxfileapp.model.ImageObj
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_display_image.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DisplayImageActivity : AppCompatActivity() {

    lateinit var path : String
    lateinit var bitmap : Bitmap

    lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)

        path = intent.getStringExtra("path")!!
        bitmap = BitmapFactory.decodeFile(path)
        imageView.setImageBitmap(bitmap)

        db = Room.databaseBuilder( applicationContext,
            AppDatabase::class.java,
            "AppDatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun showShareIntent() {
        //Create Share itent
        val intent = Intent(Intent.ACTION_SEND).setType("image/*")

        //Compress image
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        //Get URI of the image
        val uri = Uri.parse(path)

        //Put Uri as extra to share intent
        intent.putExtra(Intent.EXTRA_STREAM, uri)

        //Start/Launch Share intent
        startActivity(intent)
    }

    fun deleteBtnClick(view : View){

        val image = db.imageDao().getImageFromPath(path)
        db.imageDao().delete(image)

        val file = File(path)
        file.delete()

        val intent = Intent(this, GalleryActivity::class.java)
        intent.putExtra("fileName", image.fileName)
        startActivity(intent)
        finish()
    }

    fun shareBtnClick(view: View){
        showShareIntent()
    }

}
