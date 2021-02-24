package com.onimaskesi.cameraxfileapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageTable")
    fun getAll(): List<ImageObj>

    @Query("SELECT * FROM imageTable WHERE fileName IN (:fileName)")
    fun getImagesFromFile(fileName: String): List<ImageObj>

    @Insert
    fun insertAll(vararg images: ImageObj)

    @Delete
    fun delete(file: ImageObj)

}