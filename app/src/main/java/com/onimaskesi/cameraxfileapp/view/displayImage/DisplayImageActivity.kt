package com.onimaskesi.cameraxfileapp.view.displayImage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.onimaskesi.cameraxfileapp.R
import com.onimaskesi.cameraxfileapp.model.database.AppDatabase
import com.onimaskesi.cameraxfileapp.view.gallery.GalleryActivity
import kotlinx.android.synthetic.main.activity_display_image.*
import java.io.ByteArrayOutputStream
import java.io.File

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
