package com.example.android.mynewsapp


import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.android.mynewsapp.dataLayer.database.DBDao
import com.example.android.mynewsapp.dataLayer.database.DBObject
import com.example.android.mynewsapp.dataLayer.database.MainDBForObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
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
    fun mainDBForObjects_insertAndRead() {

        //Given a list of DBObjects
        val dbObject = DBObject(title = "title1",
            description = "description",
            urlToImage = "url to image",
            content = "this is the content")
        val dbObject2 = DBObject(title = "title2",
            description = "description",
            urlToImage = "url to image",
            content = "this is the content")

        val originalList = listOf(dbObject,dbObject2)

        //When they are inserted into the DB
        dbDao.insertDBObjects(originalList)

        //Then the exact values are retrieved
        val value = dbDao.getAllDBObjects().getOrAwaitValue()

        assertThat(value[0].title, equalToIgnoringCase ("title1"))

    }

    @ExperimentalCoroutinesApi
    @Test
    fun mainDbForObjects_insertAndGetById() = runBlockingTest{
        //Given a single DbObject that is inserted to the Db
        val toInsert = DBObject(title = "title4",
            description = "description",
            urlToImage = "url to image",
            content = "this is the content")

        //The id given after the insertions is not the same as the ID generated inside the object
      val id =  dbDao.insertDbObject(toInsert)

        // When - get get the object by ID from the DB
        val loaded = dbDao.getDbObjectById(toInsert.id)


        //Then when the object is called by its ID we get the same info entered
        assertThat<DBObject>(loaded as DBObject, notNullValue())
        assertThat(loaded.title, `is`(toInsert.title))
    }

}
