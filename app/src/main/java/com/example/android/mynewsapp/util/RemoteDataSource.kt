package com.example.android.mynewsapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.mynewsapp.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.dataLayer.retrofit.NetworkedArticleContainer
import com.example.android.mynewsapp.dataLayer.retrofit.RetrofitObject
import com.example.android.mynewsapp.repo.DataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSource(private val database: MainDBForObjects?) : DataSource {



    override fun getData(): LiveData<List<DomainObject>> {
        return MutableLiveData()
    }

    override suspend fun insertNetworkDataIntoLocalDb() {

        withContext(Dispatchers.IO){
            val newObjects = RetrofitObject.RETROFIT_SERVICE_OBJECT.getArticlesFromNetwork("ar").toDBObject()

            database?.dbDao()?.insertDBObjects(newObjects)
        }

    }

}

