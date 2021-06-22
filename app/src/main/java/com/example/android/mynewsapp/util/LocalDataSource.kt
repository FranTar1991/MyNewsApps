package com.example.android.mynewsapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.dataLayer.database.DBDao
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.repo.DataSource
import com.example.android.mynewsapp.util.DomainObject
import com.example.android.mynewsapp.util.fromDBObjectToDomainObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val database: MainDBForObjects): DataSource  {

    private var allObject: LiveData<List<DBObject>>? = null

    override fun getData(): LiveData<List<DomainObject>> {
       return Transformations.map(database.dbDao().getAllDBObjects()){
           it.fromDBObjectToDomainObject()
       }
    }

    override suspend fun insertNetworkDataIntoLocalDb() {

    }


}