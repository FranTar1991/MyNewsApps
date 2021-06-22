package com.example.android.mynewsapp.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.android.mynewsapp.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.repo.Repository

object ServiceLocatorM {
    private val lock = Any()
    private var database: MainDBForObjects? = null

    @Volatile
    var repo: Repository? = null
    @VisibleForTesting set

    fun provideRepository(context: Context): Repository{
        synchronized(this){
            return repo ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): Repository {

        return Repository(
            RemoteDataSource(MainDBForObjects.getDatabaseInstance(context)),
            LocalDataSource(MainDBForObjects.getDatabaseInstance(context))
        )
    }

}