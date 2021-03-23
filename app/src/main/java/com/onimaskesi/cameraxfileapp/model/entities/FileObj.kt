package com.onimaskesi.cameraxfileapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fileTable")
class FileObj(
        @ColumnInfo(name = "name") var name: String?,
        @PrimaryKey(autoGenerate = true) var uid: Int = 0
)

