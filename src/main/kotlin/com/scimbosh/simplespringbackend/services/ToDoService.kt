package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.exception.GeneralException
import com.scimbosh.simplespringbackend.exception.NotFoundException
import com.scimbosh.simplespringbackend.repository.ToDoRepository
import com.scimbosh.simplespringbackend.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository,
    private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun findToDoListByUserId(): List<ToDoDto>? = toDoRepository.findToDoListByUserId(getUserId())?.map { it.toDto() }

    @Deprecated("Use use findToDoListByUserId() rather this")
    fun findToDoListByUserName(username: String): List<ToDoDto>? =
        toDoRepository.findByUsername(username)?.map { it.toDto() }

    @Transactional
    fun saveToDo(dto: ToDoDto): ToDoDto? {
        var todo = dto.toEntity()
        todo.userId = getUserId()
        todo.username = SecurityContextHolder.getContext().authentication.name
        return try {
            toDoRepository.save(todo).toDto()
        } catch (e: Exception) {
            throw GeneralException(cause = dto.toString(), message = e.message)
        }
    }

    @Transactional
    fun deleteSelected(dto: ToDoDto) {
        checkingOwnerOfTodo(dto)
        toDoRepository.deleteById(dto.id!!.toInt())
    }

    @Transactional
    fun updateSelected(dto: ToDoDto): ToDoDto? {
        checkingOwnerOfTodo(dto)
        val todoEntity: ToDoEntity? = toDoRepository.findById(dto.id!!)
        todoEntity?.checked = dto.checked
        todoEntity?.content = dto.content
        return if (todoEntity != null) {
            toDoRepository.save(todoEntity).toDto()
        } else {
            throw NotFoundException(cause = "id")
        }
    }

    private fun getUserId(): Long? =
        userRepository.findByUsername(SecurityContextHolder.getContext().authentication.name)?.getId()

    private fun checkingOwnerOfTodo(todo: ToDoDto) {
        val userTodoIds = toDoRepository.findToDoListByUserId(getUserId())?.map { it.id }
        if (userTodoIds.isNullOrEmpty()) throw NotFoundException(message = "todo id not found by this user")
        if (!userTodoIds.contains(todo.id)) throw NotFoundException(message = "todo id not found by this user")
    }

    private fun ToDoEntity.toDto(): ToDoDto =
        ToDoDto(
            id = this.id,
            userId = this.userId,
            username = this.username,
            content = this.content,
            checked = this.checked
        )

    private fun ToDoDto.toEntity(): ToDoEntity =
        ToDoEntity(
            id = this.id,
            userId = this.userId,
            username = this.username,
            content = this.content,
            checked = this.checked
        )


}