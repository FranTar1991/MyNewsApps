package com.example.android.mynewsapp.dataLayer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "object_entity")
data class DBObject(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
)