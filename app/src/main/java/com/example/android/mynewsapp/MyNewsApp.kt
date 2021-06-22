package com.example.android.mynewsapp

import android.app.Application
import com.example.android.mynewsapp.repo.Repository
import com.example.android.mynewsapp.util.ServiceLocatorM

class MyNewsApp: Application() {
    val repo: Repository
    get() = ServiceLocatorM.provideRepository(this)

}