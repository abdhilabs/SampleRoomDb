package com.abdhilabs.learnroomdb.db

import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): MutableList<Todo>

    @Query("SELECT * FROM todo WHERE title LIKE :title")
    fun findByName(title: String): Todo

    @Insert
    fun insertAll(todo: Todo)

    @Delete
    fun deleteTask(todo: Todo)

    @Update
    fun updateTask(todo: Todo)

    @Query("UPDATE todo SET title=:title, deadline=:deadline, description=:desc WHERE id=:id")
    fun update(id: Long, title: String, deadline: String, desc: String)
}