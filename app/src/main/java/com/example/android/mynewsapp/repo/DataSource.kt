package com.example.android.mynewsapp.repo

import androidx.lifecycle.LiveData
import com.example.android.mynewsapp.util.DomainObject

interface DataSource {

    fun getData(): LiveData<List<DomainObject>>
    suspend fun insertNetworkDataIntoLocalDb()
}
