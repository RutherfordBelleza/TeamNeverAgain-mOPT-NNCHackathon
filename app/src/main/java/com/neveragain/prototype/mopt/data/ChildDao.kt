package com.neveragain.prototype.mopt.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChildDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChild(child: Child)

    @Query("SELECT * FROM child_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Child>>
}