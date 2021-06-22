package com.example.android.mynewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.repo.DataSource
import com.example.android.mynewsapp.util.DomainObject
import com.example.android.mynewsapp.util.fromDBObjectToDomainObject

class FakeDataSource(
    private val fakeData: MutableList<DBObject>): DataSource {

    private val _livedataFromFakeSource = MutableLiveData<MutableList<DBObject>>(fakeData)
    private val livedataFromFakeSource: LiveData<List<DomainObject>>
        get() = Transformations.map(_livedataFromFakeSource){
            it.fromDBObjectToDomainObject()
        }

    override fun getData(): LiveData<List<DomainObject>>{
        return livedataFromFakeSource
    }


    override suspend fun insertNetworkDataIntoLocalDb() {
        val list =  _livedataFromFakeSource.value
         list?.addAll(fakeData)
        _livedataFromFakeSource.value = list
    }
    companion object{
      fun getLiveDataAddedToRemoteSource(fake: FakeDataSource) = fake.getData()
    }

}