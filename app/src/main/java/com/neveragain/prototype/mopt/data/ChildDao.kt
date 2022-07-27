package com.neveragain.prototype.mopt.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChildDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChild(child: Child)

    @Update
    suspend fun updateChild(child: Child)

    @Query("SELECT * FROM child_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<Child>>

    @Query("SELECT * FROM child_table ORDER BY id ASC")
    suspend fun selectAll(): List<Child>

    @Query("DELETE FROM child_table")
    suspend fun deleteAll()

}