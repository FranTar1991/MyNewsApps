package com.example.android.mynewsapp.allFragments.dataLayer.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DBDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertDBObjects(dBObjects: List<DBObject>)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertDbObject(dbObject: DBObject): Long

    @Query("SELECT * from object_entity")
     fun getAllDBObjects(): LiveData<List<DBObject>>

     @Query("SELECT * from object_entity WHERE id= :articleId")
     suspend fun getDbObjectById(articleId: Int): DBObject
}

@Database(entities = [DBObject::class], version = 1, exportSchema = false)
abstract class MainDBForObjects: RoomDatabase(){
    abstract fun dbDao(): DBDao

    companion object {

        @Volatile
        private var INSTANCE: MainDBForObjects? = null

        fun getDatabaseInstance (context: Context): MainDBForObjects{

          return  synchronized(this){
                var instance = INSTANCE
                if (instance == null){

                    instance = Room.databaseBuilder(
                        context,
                        MainDBForObjects::class.java, "database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
              instance
            }


        }


    }
//    companion object {
//
//        @Volatile
//        private var sInstance: MainDBForObjects? = null
//
//        @JvmStatic
//        fun getInstance(context: Context): MainDBForObjects {
//            if (sInstance != null) {
//                return sInstance
//            }
//
//            return synchronized(this) {
//                var instance = sInstance
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        MainDBForObjects::class.java,
//                        "sleep_history_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    sInstance = instance
//                }
//
//                instance
//            }
//        }
//    }
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