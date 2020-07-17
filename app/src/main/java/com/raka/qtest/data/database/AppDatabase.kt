package com.raka.qtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raka.qtest.data.model.ProductLocal
import com.raka.qtest.utils.Constants.Companion.DB_NAME
import com.raka.qtest.utils.Constants.Companion.DB_VERSION

@Database(entities = [ProductLocal::class],version = DB_VERSION)
abstract class AppDatabase:RoomDatabase() {
    abstract fun parametersDao(): ParametersDao
    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context:Context): AppDatabase {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                        DB_NAME)
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}