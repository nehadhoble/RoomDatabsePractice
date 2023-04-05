package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student :: class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    //this calss is responsible for creating actual database instance
    abstract fun studentDao(): StudentDao

    //using singleton pattern because we want only one instance of database throughout the app
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            //if the first time app database is created and we do not have the instace of it
            //that time will create a instance of that

            //sync- first it will wait single operation to be completed
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}