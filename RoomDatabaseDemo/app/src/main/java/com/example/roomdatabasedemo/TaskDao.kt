package com.example.roomdatabasedemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table WHERE user_id = :userId")
    suspend fun getTasksForUser(userId: Int): List<Task>
}