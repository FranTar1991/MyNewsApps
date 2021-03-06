package com.example.android.mynewsapp.allFragments.util

import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.dataLayer.database.DBObject
import com.example.android.mynewsapp.allFragments.dataLayer.retrofit.NetworkedArticleContainer


fun NetworkedArticleContainer.toDBObject(): List<DBObject>{
    return articles.map {
        DBObject(title = it.title,
        description = it.description,
        urlToImage = it.urlToImage,
        content = it.content)
    }
}

fun List<DBObject>.fromDBObjectToDomainObject(): List<DomainObject>{
    return map {
        DomainObject(
            id = it.id,
            title = it.title,
            content = it.content,
            description = it.description,
            urlToImage = it.urlToImage,
        )

    }
}