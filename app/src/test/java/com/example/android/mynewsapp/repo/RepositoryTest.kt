package com.example.android.mynewsapp.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.android.mynewsapp.FakeDataSource
import com.example.android.mynewsapp.MainCoroutineRule
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.getOrAwaitValue2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase


@ExperimentalCoroutinesApi
@SmallTest
class RepositoryTest{

    //Given a list of DBObjects
    val remoteDbObject1 = DBObject(title = "Remote1", description = "description", urlToImage = "url to image", content = "this is the content")
    val remoteDbObject2 = DBObject(title = "Remote2", description = "description", urlToImage = "url to image", content = "this is the content")
    val remoteDbObject3 = DBObject(title = "Remote3", description = "description", urlToImage = "url to image", content = "this is the content")
    val localDbObject4 = DBObject(title = "Local4", description = "description", urlToImage = "url to image", content = "this is the content")

    val remoteData = listOf(remoteDbObject1, remoteDbObject2, remoteDbObject3).sortedBy { it.id }
    val localData = listOf(localDbObject4).sortedBy { it.id }

    private lateinit var remoteDataSource: FakeDataSource
    private lateinit var localDataSource: FakeDataSource

    private lateinit var repositoryToTest: Repository

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepo(){

        remoteDataSource = FakeDataSource(remoteData.toMutableList())
        localDataSource = FakeDataSource(localData.toMutableList())

        repositoryToTest = Repository(localDataSource, remoteDataSource, Dispatchers.Main)

    }

    @Test
    fun allArticlesFromDb_GetAllTheArticlesFromLocalDataSource() {
        val allArticles = repositoryToTest.getArticlesFromDB().getOrAwaitValue2()

        assertThat(allArticles.size, equalTo(localData.size))
    }

    @Test
    fun setDataFromNetwork_updateLocalData()= mainCoroutineRule.runBlockingTest {


       repositoryToTest.setDataFromNetwork()
        val allArticles = FakeDataSource.getLiveDataAddedToRemoteSource(remoteDataSource).getOrAwaitValue2()

        assertThat(remoteData[0].title, equalToIgnoringCase (allArticles[0].title))
    }


}