package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.repository.ToDoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Transactional
    fun saveToDo(dto: ToDoDto): ToDoDto? {
        return toDoRepository.save(dto.toEntity()).toDto()
    }

    @Transactional
    fun deleteSelected(dto: ToDoDto) {
        //toDoRepository.delete(dto.toEntity())
        toDoRepository.deleteById(dto.id!!.toInt())
    }

    @Transactional
    fun updateSelected(dto: ToDoDto): ToDoDto? {

        //toDoRepository.update(dto.toEntity())?.toDto()

        var todoEntity: ToDoEntity? =  toDoRepository.findById(dto.id!!)
        todoEntity?.checked = dto.checked
        todoEntity?.content = dto.content
        return if(todoEntity != null){
            toDoRepository.save(todoEntity).toDto()
        } else {
            null
        }
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
            id = this.id,
            username = this.username,
            content = this.content,
            checked = this.checked
        )
}