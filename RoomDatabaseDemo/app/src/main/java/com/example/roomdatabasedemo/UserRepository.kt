package com.example.roomdatabasedemo

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun updateUser(name: String, age: Int) {
        userDao.updateUser(name, age)
    }

    suspend fun deleteUser(name: String, age: Int) {
        userDao.deleteUser(name, age)
    }

    suspend fun searchByName(name: String): List<User> {
        return userDao.searchByName("%$name%")
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }
}