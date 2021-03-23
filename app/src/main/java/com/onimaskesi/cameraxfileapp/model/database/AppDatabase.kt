package com.onimaskesi.cameraxfileapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onimaskesi.cameraxfileapp.model.dataAccessLayer.FileDao
import com.onimaskesi.cameraxfileapp.model.dataAccessLayer.ImageDao
import com.onimaskesi.cameraxfileapp.model.entities.FileObj
import com.onimaskesi.cameraxfileapp.model.entities.ImageObj

@Database(entities = arrayOf(FileObj::class, ImageObj::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
    abstract fun imageDao(): ImageDao
}