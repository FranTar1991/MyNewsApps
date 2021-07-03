package com.example.android.mynewsapp.allFragments.dataLayer.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.util.DataSourceInterface
import com.example.android.mynewsapp.allFragments.util.fromDBObjectToDomainObject

class LocalDataSource(private val database: MainDBForObjects): DataSourceInterface {

    private var allObject: LiveData<List<DBObject>>? = null

    override fun getData(): LiveData<List<DomainObject>> {
       return Transformations.map(database.dbDao().getAllDBObjects()){
           it.fromDBObjectToDomainObject()
       }
    }

    override suspend fun insertNetworkDataIntoLocalDb() {

    }


}