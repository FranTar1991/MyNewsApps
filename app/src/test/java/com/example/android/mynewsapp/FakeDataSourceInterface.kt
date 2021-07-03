package com.example.android.mynewsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mynewsapp.allFragments.dataLayer.database.DBObject
import com.example.android.mynewsapp.allFragments.util.DataSourceInterface
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import com.example.android.mynewsapp.allFragments.util.fromDBObjectToDomainObject

class FakeDataSourceInterface(
    private val fakeData: MutableList<DBObject>): DataSourceInterface {

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
      fun getLiveDataAddedToRemoteSource(fake: FakeDataSourceInterface) = fake.getData()
    }

}