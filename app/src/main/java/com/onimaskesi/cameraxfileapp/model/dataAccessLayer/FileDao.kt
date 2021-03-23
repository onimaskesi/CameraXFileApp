package com.onimaskesi.cameraxfileapp.model.dataAccessLayer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.onimaskesi.cameraxfileapp.model.entities.FileObj

@Dao
interface FileDao {
    @Query("SELECT * FROM fileTable")
    fun getAll(): List<FileObj>

    @Query("SELECT * FROM fileTable WHERE uid IN (:fileIds)")
    fun loadAllByIds(fileIds: IntArray): List<FileObj>

    @Insert
    fun insertAll(vararg files: FileObj)

    @Delete
    fun delete(file: FileObj)
}