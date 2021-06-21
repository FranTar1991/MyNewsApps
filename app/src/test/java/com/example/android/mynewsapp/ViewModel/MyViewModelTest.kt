package com.example.android.mynewsapp.ViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.android.mynewsapp.FakeRepo
import com.example.android.mynewsapp.MainCoroutineRule
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.getOrAwaitValue2
import com.example.android.mynewsapp.repo.RepoInterface
import com.example.android.mynewsapp.repo.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.core.IsNull.notNullValue
import org.hamcrest.text.IsEqualIgnoringCase

@ExperimentalCoroutinesApi
@SmallTest
class MyViewModelTest{


    //Given a list of DBObjects
    private val remoteDbObject1 = DBObject(title = "Remote1", description = "description", urlToImage = "url to image", content = "this is the content")
    private val remoteDbObject2 = DBObject(title = "Remote2", description = "description", urlToImage = "url to image", content = "this is the content")
    private val remoteDbObject3 = DBObject(title = "Remote3", description = "description", urlToImage = "url to image", content = "this is the content")
    private val localDbObject = DBObject(title = "Local4", description = "description", urlToImage = "url to image", content = "this is the content")


    private val remoteData = listOf(remoteDbObject1, remoteDbObject2, remoteDbObject3).sortedBy { it.id }
    private val localData = listOf(localDbObject)

    //Class under test
    private lateinit var viewModel: MyViewModel
    private lateinit var fakeRepo: RepoInterface

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUpViewModel(){
        fakeRepo = FakeRepo(localData.toMutableList(), remoteData.toMutableList())
    }

    @Test
    fun allObjects_requestTheData_getAllObjectsOnDb() = mainCoroutineRule.runBlockingTest{

        viewModel = MyViewModel(fakeRepo)
        viewModel.refreshData()
        val allValues = viewModel.allObjects.getOrAwaitValue2()

        assertThat(allValues.get(0).title,
            IsEqualIgnoringCase.equalToIgnoringCase(remoteData.get(0).title)
        )
    }
}