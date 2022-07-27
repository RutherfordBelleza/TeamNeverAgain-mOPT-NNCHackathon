package com.neveragain.prototype.mopt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Child::class], version = 5, exportSchema = false)
abstract class ChildDatabase : RoomDatabase() {

    abstract fun childDao(): ChildDao

    companion object {
        @Volatile
        private var INSTANCE: ChildDatabase? = null

        fun getDatabase(context: Context): ChildDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChildDatabase::class.java,
                    "child_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}