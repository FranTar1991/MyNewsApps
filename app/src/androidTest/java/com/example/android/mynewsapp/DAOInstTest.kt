package com.example.android.mynewsapp


import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.mynewsapp.database.DBDao
import com.example.android.mynewsapp.database.DBObject
import com.example.android.mynewsapp.database.MainDBForObjects
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class DAOInstTest {
    private lateinit var dbDao: DBDao
    private lateinit var database: MainDBForObjects

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDB(){

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MainDBForObjects::class.java)
            .build()
        dbDao = database.dbDao()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun addArticleToDb() {
        val dbObject = DBObject(title = "title1",
            description = "description",
            urlToImage = "url to image",
            content = "this is the content")
        val dbObject2 = DBObject(title = "title2",
            description = "description",
            urlToImage = "url to image",
            content = "this is the content")

        val originalList = listOf(dbObject,dbObject2)
        dbDao.insertDBObjects(originalList)

        val value = dbDao.getAllDBObjects().getOrAwaitValue()

        Log.i("AndroidTesting",value[0].title)
        assertThat(value[0].title, equalToIgnoringCase ("title1"))

    }

}
