package com.onimaskesi.cameraxfileapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageTable")
class ImageObj(
    @ColumnInfo(name = "fileName") var fileName : String,
    @ColumnInfo(name = "path") var path : String,
    @PrimaryKey(autoGenerate = true) var uid : Int = 0
)