package com.example.android.mynewsapp.allFragments.repo

import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject

interface RepoInterface {

    fun getArticlesFromDB(): LiveData<List<DomainObject>>
    suspend fun setDataFromNetwork()

}