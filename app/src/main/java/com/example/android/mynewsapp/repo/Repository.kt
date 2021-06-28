package com.example.android.mynewsapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.dataLayer.retrofit.RetrofitObject
import com.example.android.mynewsapp.util.DomainObject
import com.example.android.mynewsapp.util.toDBObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository(
    private val localDataSource: DataSource,
    private val remoteDataSource: DataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : RepoInterface {

    override fun getArticlesFromDB(): LiveData<List<DomainObject>>{
        return localDataSource.getData()
    }

     override suspend fun setDataFromNetwork (){

         withContext(ioDispatcher){
             launch {

                 remoteDataSource.insertNetworkDataIntoLocalDb()
             }
         }

    }

}