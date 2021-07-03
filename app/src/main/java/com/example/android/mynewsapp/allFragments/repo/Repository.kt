package com.example.android.mynewsapp.allFragments.repo

import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.util.DataSourceInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(
    private val localDataSourceInterface: DataSourceInterface,
    private val remoteDataSourceInterface: DataSourceInterface,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : RepoInterface {

    override fun getArticlesFromDB(): LiveData<List<DomainObject>>{
        return localDataSourceInterface.getData()
    }

     override suspend fun setDataFromNetwork (){

         withContext(ioDispatcher){
             launch {

                 remoteDataSourceInterface.insertNetworkDataIntoLocalDb()
             }
         }

    }


}