package com.example.android.mynewsapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.dataLayer.retrofit.RetrofitObject
import com.example.android.mynewsapp.dataLayer.retrofit.RetrofitService
import com.example.android.mynewsapp.util.DomainObject
import com.example.android.mynewsapp.util.fromDBObjectToDomainObject
import com.example.android.mynewsapp.util.toDBObject

class Repository(private val databaseObjects: MainDBForObjects) {

    val allArticlesFromDb: LiveData<List<DomainObject>> =
        Transformations.map(databaseObjects.dbDao().getAllDBObjects()) {
            it.fromDBObjectToDomainObject()
        }

    suspend fun getDataFromNetwork(country: String){
      val articlesFromNetwork =  RetrofitObject.RETROFIT_SERVICE_OBJECT.getArticlesFromNetwork(country)
        databaseObjects.dbDao().insertDBObjects(articlesFromNetwork.toDBObject())

    }

}