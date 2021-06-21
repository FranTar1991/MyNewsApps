package com.example.android.mynewsapp.repo

import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.util.DomainObject

interface RepoInterface {

    fun getArticlesFromDB(): LiveData<List<DomainObject>>
    suspend fun setDataFromNetwork()

}