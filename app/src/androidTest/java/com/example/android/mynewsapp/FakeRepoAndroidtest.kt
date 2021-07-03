package com.example.android.mynewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.allFragments.dataLayer.database.DBObject
import com.example.android.mynewsapp.allFragments.repo.RepoInterface
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.util.fromDBObjectToDomainObject

class FakeRepoAndroidTest(private val fakeLocal: MutableList<DBObject>,
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