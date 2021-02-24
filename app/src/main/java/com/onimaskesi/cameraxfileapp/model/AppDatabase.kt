package com.onimaskesi.cameraxfileapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FileObj::class, ImageObj::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
    abstract fun imageDao(): ImageDao
}