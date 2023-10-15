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

    private fun ToDoEntity.toDto(): ToDoDto =
        ToDoDto(
            id = this.id,
            userId = this.userId,
            content = this.content,
            checked = this.checked
        )

    private fun ToDoDto.toEntity(): ToDoEntity =
        ToDoEntity(
            id = 0,
            userId = this.userId,
            content = this.content,
            checked = this.checked
        )
}