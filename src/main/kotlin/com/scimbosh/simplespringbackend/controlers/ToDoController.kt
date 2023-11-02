package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.services.ToDoService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @GetMapping("/hc")
    fun userIndex(): String = "/todo/hc - OK"

    @GetMapping("/list")
    fun list(): List<ToDoDto>? = toDoService.findToDoListByUserId()

    @PostMapping("/add")
    //@PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addItem(@RequestBody dto: ToDoDto): ToDoDto? = toDoService.saveToDo(dto)

    @DeleteMapping
    fun delete(@RequestBody dto: ToDoDto, principal: Principal): ResponseEntity<Any> {
        logger.info("Name dto = ${dto.username}  namePrincipal = ${principal.name}")
        if (dto.username == null) dto.username = principal.name
        if (dto.username != principal.name) {
            return ResponseEntity<Any>(dto, HttpStatus.FORBIDDEN)
        }
        return ResponseEntity<Any>(toDoService.deleteSelected(dto), HttpStatus.OK)
    }

    @PatchMapping
    fun update(@RequestBody dto: ToDoDto, principal: Principal): ToDoDto? = toDoService.updateSelected(dto)


}