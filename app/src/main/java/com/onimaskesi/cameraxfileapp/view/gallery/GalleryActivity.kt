package com.onimaskesi.cameraxfileapp.view.gallery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.onimaskesi.cameraxfileapp.R
import com.onimaskesi.cameraxfileapp.adapter.GalleryRecyclerAdapter
import com.onimaskesi.cameraxfileapp.model.database.AppDatabase
import com.onimaskesi.cameraxfileapp.model.entities.ImageObj
import kotlinx.android.synthetic.main.activity_gallery.*
import java.io.File

class GalleryActivity : AppCompatActivity() {

    lateinit var fileName : String

    lateinit var db : AppDatabase

    lateinit var imagesList : ArrayList<ImageObj>
    lateinit var galleryRecyclerViewAdapter : GalleryRecyclerAdapter

    //request code to pick image(s)
    private val PICK_IMAGES_CODE = 0

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

        updateImages()
        updateImageList()
        updateRV()

    }

    fun pickImagesIntent(){
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE)
    }

    fun updateRV(){
        //Recycler View
        imagesRV.layoutManager = GridLayoutManager(this, 3)
        galleryRecyclerViewAdapter = GalleryRecyclerAdapter(this , imagesList)
        imagesRV.adapter = galleryRecyclerViewAdapter
        //imagesRV.setNestedScrollingEnabled(false)
        //imagesRV.setHasFixedSize(true)
        //imagesRV.setItemViewCacheSize(10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_IMAGES_CODE){
            if(requestCode == Activity.RESULT_OK){
                if(data!!.clipData != null){
                    //picked mutiple images
                    //get number of picked images
                    val count = data.clipData!!.itemCount
                    for(i in 0 until count){
                        val imagePath = data.clipData!!.getItemAt(i).uri.path!!
                        //add image to list
                        val image =
                            ImageObj(
                                fileName,
                                imagePath!!
                            )
                        db.imageDao().insertAll(image!!)
                        imagesList.add(image!!)
                    }

                } else {
                    //picked single image
                }
                updateRV()
            }
        }
    }

    fun updateImageList(){
        imagesList = ArrayList(db.imageDao().getImagesFromFile(fileName))
    }

    fun updateImages(){
        updateImageList()
        for(image in imagesList){
            if(!File(image.path).exists()){
                db.imageDao().delete(image)
            }
        }
        imagesList.clear()
    }
}