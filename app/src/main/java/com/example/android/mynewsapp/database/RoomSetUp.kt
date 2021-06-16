package com.example.android.mynewsapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DBDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertDBObjects(dBObjects: List<DBObject>)

    @Query("SELECT * from DBObject")
     fun getAllDBObjects(): LiveData<List<DBObject>>
}

@Database(entities = [DBObject::class], version = 1, exportSchema = false)
abstract class MainDBForObjects: RoomDatabase(){
    abstract fun dbDao(): DBDao

    companion object {

        @Volatile
        private var INSTANCE: MainDBForObjects? = null

        fun getDatabaseInstance (context: Context): MainDBForObjects{

            synchronized(this){
                var instance = INSTANCE
                if (instance == null){

                    instance = Room.databaseBuilder(
                        context,
                        MainDBForObjects::class.java, "database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }


        }


    }
}




private lateinit var INSTANCE2: MainDBForObjects
fun getDBInstance(context: Context): MainDBForObjects{
    if (!::INSTANCE2.isInitialized){
        INSTANCE2 = Room.databaseBuilder(context,MainDBForObjects::class.java,"database")
            .fallbackToDestructiveMigration()
            .build()
    }
    return INSTANCE2
}