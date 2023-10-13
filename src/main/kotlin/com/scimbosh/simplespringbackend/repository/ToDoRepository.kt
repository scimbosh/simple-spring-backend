package com.scimbosh.simplespringbackend.repository

import com.scimbosh.simplespringbackend.entities.ToDoEntity
import org.springframework.data.repository.CrudRepository


interface ToDoRepository: CrudRepository<ToDoEntity, Int> {
}