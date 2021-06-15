package com.example.android.mynewsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBObject(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val urlToImage: String,
    val content: String,
)