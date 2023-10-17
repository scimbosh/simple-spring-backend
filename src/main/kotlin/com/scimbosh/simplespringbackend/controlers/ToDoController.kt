package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.ToDoService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.security.Principal
import kotlin.contracts.contract

@RestController
@RequestMapping(value = ["/todo"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
//@CrossOrigin(origins = ["*"], maxAge = 86400)
class ToDoController(
    private val toDoService: ToDoService
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @PostMapping("/add")
    //@PostMapping("/add",
    //consumes = [MediaType.APPLICATION_JSON_VALUE],
    //produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addItem(@RequestBody dto: ToDoDto): Any {
        return if (toDoService.saveToDo(dto) != null) {
            dto
        } else {
            BodyContent(isSuccessful = false, obj = dto)
        }
    }

    @GetMapping("/list")
    fun list(principal: Principal): List<ToDoEntity>? {
        logger.error("TEEEEEEEEEEEST2222")
        println("TEEEEEEEEEEEST")
        println("name = ${principal.name}")
        return toDoService.findToDoListByUser(principal.name)
    }


    @GetMapping("/hc")
    fun userIndex(): String {
        return "/todo/hc - OK"
    }

}