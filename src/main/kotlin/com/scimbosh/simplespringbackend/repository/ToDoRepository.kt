package com.scimbosh.simplespringbackend.repository

import com.scimbosh.simplespringbackend.entities.ToDoEntity
import org.springframework.data.repository.CrudRepository

interface ToDoRepository : CrudRepository<ToDoEntity, Int> {

    fun findByUsername(username: String): List<ToDoEntity>?

    fun findById(id: Long): ToDoEntity?

    fun findToDoListByUserId(userId: Long?): List<ToDoEntity>?

}