package com.example.android.mynewsapp

import android.app.Application
import com.example.android.mynewsapp.allFragments.repo.RepoInterface
import com.example.android.mynewsapp.allFragments.util.ServiceLocatorM

class MyNewsApp: Application() {
    val repo: RepoInterface
    get() = ServiceLocatorM.provideRepository(this)

}