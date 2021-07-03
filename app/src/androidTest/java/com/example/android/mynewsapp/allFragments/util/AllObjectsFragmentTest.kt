package com.example.android.mynewsapp.allFragments.util

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.mynewsapp.FakeRepoAndroidTest
import com.example.android.mynewsapp.R
import com.example.android.mynewsapp.allFragments.dataLayer.database.DBObject
import com.example.android.mynewsapp.allFragments.repo.RepoInterface
import com.example.android.mynewsapp.allFragments.ui.AllObjectsAdapter
import com.example.android.mynewsapp.allFragments.ui.AllObjectsFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class AllObjectsFragmentTest{

    private lateinit var repository: RepoInterface

    @Before
    fun initRepository() {
        //Given a list of DBObjects
        val remoteDbObject1 = DBObject(title = "TitleRemote1", description = "description", content = "this is the content")
        val remoteDbObject2 = DBObject(title = "TitleRemote2", description = "description", content = "this is the content")
        val remoteDbObject3 = DBObject(title = "TitleRemote3", description = "description", content = "this is the content")
        val localDbObject4 = DBObject(title = "Local4", description = "description", content = "this is the content")

        val remoteData = listOf(remoteDbObject1, remoteDbObject2, remoteDbObject3).sortedBy { it.id }
        val localData = listOf(localDbObject4).sortedBy { it.id }
        repository = FakeRepoAndroidTest(fakeLocal = localData.toMutableList(), fakeRemote =  remoteData.toMutableList())
        ServiceLocatorM.repo = repository
    }

    @After
    fun cleanupDb()  {
        ServiceLocatorM.resetRepository()
    }


    @Test
    fun allObjectsFragmentTest_displayObject(){

        //Given an object
        //when allObjectsFragmentTest is called
        val scenario = launchFragmentInContainer<AllObjectsFragment>(Bundle(), R.style.Theme_MyNewsApp)
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<AllObjectsAdapter.AllObjectsViewHolder>(2,click()))

        Thread.sleep(15000)
    }
}