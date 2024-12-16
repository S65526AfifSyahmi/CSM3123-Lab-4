package com.example.roomdatabasedemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun getAllUsers(): LiveData<List<User>> {
        return liveData {
            val users = repository.getAllUsers()
            emit(users)
        }
    }

    fun updateUser(name: String, age: Int) {
        viewModelScope.launch {
            repository.updateUser(name, age)
        }
    }

    fun deleteUser(name: String, age: Int) {
        viewModelScope.launch {
            repository.deleteUser(name, age)
        }
    }

    fun searchByName(name: String): LiveData<List<User>> {
        return liveData {
            val results = repository.searchByName(name)
            emit(results)
        }
    }

    fun update(user: User) {
        viewModelScope.launch {
            repository.update(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            repository.delete(user)
        }
    }
}