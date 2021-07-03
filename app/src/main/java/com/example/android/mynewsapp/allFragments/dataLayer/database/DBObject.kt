package com.example.android.mynewsapp.allFragments.dataLayer.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "object_entity")
data class DBObject(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val description: String?,
    val urlToImage: String? = "images.unsplash.com/photo-1612278920639-cfbae3835fee?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZG9uYWxkJTIwdHJ1bXB8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60",
    val content: String?,
)