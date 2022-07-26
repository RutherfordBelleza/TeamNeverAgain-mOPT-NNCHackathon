package com.neveragain.prototype.mopt.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Child>>
    private val repository: ChildRepository

    init {
        val childDao = ChildDatabase.getDatabase(application).childDao()
        repository = ChildRepository(childDao)
        readAllData = repository.readAllData
    }

    fun addChild(child: Child) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChild(child)
        }
    }

}