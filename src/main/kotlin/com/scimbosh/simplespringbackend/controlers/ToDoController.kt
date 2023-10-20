package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.ToDoService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
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

    @DeleteMapping("/delete")
    fun delete(@RequestBody dto: ToDoDto, principal: Principal): Any? {
        logger.warn("Name dto = ${dto.username}  namePrincipal = ${principal.name}")
        if (dto.username != principal.name) {
            return BodyContent(isSuccessful = false, obj = dto)
        }
        return if (dto.username == null) {
            dto.username = principal.name
            toDoService.deleteSelected(dto)
        }else{
            toDoService.deleteSelected(dto)
        }
    }

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/todo/hc - OK"
    }

}