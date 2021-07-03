package com.example.android.mynewsapp.allFragments.util

import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject

interface DataSourceInterface {

    fun getData(): LiveData<List<DomainObject>>
    suspend fun insertNetworkDataIntoLocalDb()
}
