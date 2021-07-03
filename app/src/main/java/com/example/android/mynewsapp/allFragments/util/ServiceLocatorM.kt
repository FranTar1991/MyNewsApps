package com.example.android.mynewsapp.allFragments.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.android.mynewsapp.allFragments.dataLayer.database.LocalDataSource
import com.example.android.mynewsapp.allFragments.dataLayer.retrofit.RemoteDataSourceInterface
import com.example.android.mynewsapp.allFragments.dataLayer.database.MainDBForObjects
import com.example.android.mynewsapp.allFragments.repo.RepoInterface
import com.example.android.mynewsapp.allFragments.repo.Repository

object ServiceLocatorM {
    private val lock = Any()
    private var database: MainDBForObjects? = null


    @Volatile
    var repo: RepoInterface? = null
    @VisibleForTesting set

    fun provideRepository(context: Context): RepoInterface{
        synchronized(this){
            return repo ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): RepoInterface {

        database = MainDBForObjects.getDatabaseInstance(context)
        return Repository(
            LocalDataSource(MainDBForObjects.getDatabaseInstance(context)),
            RemoteDataSourceInterface(database),
        )
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            repo = null
        }
    }


}