package com.example.android.mynewsapp.allFragments.dataLayer.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.allFragments.util.DataSourceInterface
import com.example.android.mynewsapp.allFragments.util.toDBObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RemoteDataSourceInterface(private val database: MainDBForObjects?) : DataSourceInterface {



    override fun getData(): LiveData<List<DomainObject>> {
        return MutableLiveData()
    }

    override suspend fun insertNetworkDataIntoLocalDb() {

            withContext(Dispatchers.IO){
                launch {
                    val newObjects = RetrofitObject.RETROFIT_SERVICE_OBJECT
                        .getArticlesFromNetwork("ar").toDBObject()

                    database?.dbDao()?.insertDBObjects(newObjects)
                }
            }



    }

}

