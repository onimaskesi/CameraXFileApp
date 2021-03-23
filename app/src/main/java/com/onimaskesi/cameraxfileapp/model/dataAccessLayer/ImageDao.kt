package com.onimaskesi.cameraxfileapp.model.dataAccessLayer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.onimaskesi.cameraxfileapp.model.entities.ImageObj

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageTable")
    fun getAll(): List<ImageObj>

    @Query("SELECT * FROM imageTable WHERE fileName IN (:fileName)")
    fun getImagesFromFile(fileName: String): List<ImageObj>

    @Query("SELECT * FROM imageTable WHERE path IN (:path)")
    fun getImageFromPath(path: String): ImageObj

    @Insert
    fun insertAll(vararg images: ImageObj)

    @Delete
    fun delete(file: ImageObj)

}