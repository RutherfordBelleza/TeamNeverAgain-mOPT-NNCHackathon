package com.neveragain.prototype.mopt.data

import androidx.lifecycle.LiveData

class ChildRepository(private val childDAO: ChildDao) {
    val readAllData: LiveData<List<Child>> = childDAO.getAllData()
    suspend fun addChild(child: Child) {
        childDAO.addChild(child)
    }
}