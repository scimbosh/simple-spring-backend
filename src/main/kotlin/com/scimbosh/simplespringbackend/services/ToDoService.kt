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
        var result : ToDoEntity = toDoRepository.save(dto.toEntity())
        logger.info("Result of save in DB ${result.toString()}")
        logger.info("Result of save in DB ${result.id.toString()}")
        return result.toDto()
    }

    @Transactional
    fun deleteSelected(dto: ToDoDto) {
        logger.info("Result of DELETE in DB ")
        logger.info("Result of DELETE in DB ${dto.toEntity().id.toString()}")
        logger.info("Result of DELETE in DB ${dto.toEntity().username.toString()}")
        logger.info("Result of DELETE in DB ${dto.toEntity().content.toString()}")
        logger.info("Result of DELETE in DB ${dto.toEntity().checked.toString()}")
        toDoRepository.delete(dto.toEntity())
        //toDoRepository.deleteById(dto.id!!.toInt())

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