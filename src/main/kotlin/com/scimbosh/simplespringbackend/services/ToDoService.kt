package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.repository.ToDoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository
) {

    @Transactional
    fun saveToDo(dto: ToDoDto): ToDoDto? {
        return toDoRepository.save(dto.toEntity()).toDto()
    }

    fun findToDoListByUser(username: String): List<ToDoEntity>? =
        toDoRepository.findByUsername(username)


    private fun ToDoEntity.toDto(): ToDoDto =
        ToDoDto(
            id = this.id,
            username = this.username,
            content = this.content,
            checked = this.checked
        )

    private fun ToDoDto.toEntity(): ToDoEntity =
        ToDoEntity(
            id = 0,
            username = this.username,
            content = this.content,
            checked = this.checked
        )
}