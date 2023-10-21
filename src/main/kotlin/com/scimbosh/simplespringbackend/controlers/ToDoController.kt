package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.ToDoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal

@RestController
@RequestMapping(value = ["/todo"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
//@CrossOrigin(origins = ["*"], maxAge = 86400)
class ToDoController(
    private val toDoService: ToDoService
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @PostMapping("/add")
    //@PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addItem(@RequestBody dto: ToDoDto, principal: Principal): Any {
        dto.username = principal.name
        return toDoService.saveToDo(dto) ?: BodyContent(isSuccessful = false, obj = dto)
    }

    @GetMapping("/list")
    fun list(principal: Principal): List<ToDoEntity>? {
        return toDoService.findToDoListByUser(principal.name)
    }

    @DeleteMapping
    fun delete(@RequestBody dto: ToDoDto, principal: Principal): Any? {
        logger.warn("Name dto = ${dto.username}  namePrincipal = ${principal.name}")
        if (dto.username == null) dto.username = principal.name
        if (dto.username != principal.name) {
            return ResponseStatusException(HttpStatus.FORBIDDEN)
        }
        return toDoService.deleteSelected(dto)
    }

    @PatchMapping
    fun update(@RequestBody dto: ToDoDto, principal: Principal): Any? {
        logger.info("Update todo = ${dto.toString()}  ${dto.id.toString()}")
        return if (dto.username != principal.name) {
            //ResponseEntity.status(403) // not working
            ResponseStatusException(HttpStatus.FORBIDDEN)
        } else if (dto.id == null) {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            toDoService.updateSelected(dto) ?: ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/todo/hc - OK"
    }

}