package com.example.android.mynewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.repo.RepoInterface
import com.example.android.mynewsapp.util.DomainObject
import com.example.android.mynewsapp.util.fromDBObjectToDomainObject

class FakeRepo(private val fakeLocal: MutableList<DBObject>,
               private val fakeRemote: MutableList<DBObject>): RepoInterface {

    private val _livedataFromFakeSource= MutableLiveData<MutableList<DBObject>>(fakeLocal)

    override fun getArticlesFromDB(): LiveData<List<DomainObject>> {
        return Transformations.map(_livedataFromFakeSource){
            it.fromDBObjectToDomainObject()
        }
    }

    override suspend fun setDataFromNetwork() {

        _livedataFromFakeSource.value = fakeRemote

    }
}