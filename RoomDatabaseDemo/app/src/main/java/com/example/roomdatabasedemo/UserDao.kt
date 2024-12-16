package com.example.roomdatabasedemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_table WHERE name LIKE :name")
    suspend fun searchByName(name: String): List<User>

    @Query("UPDATE user_table SET age = :age WHERE name = :name")
    suspend fun updateUser(name: String, age: Int)

    @Query("DELETE FROM user_table WHERE name = :name AND age = :age")
    suspend fun deleteUser(name: String, age: Int)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}