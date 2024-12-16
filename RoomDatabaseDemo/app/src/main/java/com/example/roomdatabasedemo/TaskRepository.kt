package com.example.roomdatabasedemo

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun getTasksForUser(userId: Int): List<Task> {
        return taskDao.getTasksForUser(userId)
    }
}