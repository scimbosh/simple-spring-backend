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

    @PostMapping("/add")
    //@PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addItem(@RequestBody dto: ToDoDto, principal: Principal): ResponseEntity<Any> {
        dto.username = principal.name
        val result = toDoService.saveToDo(dto)
        return if (result != null) {
            ResponseEntity<Any>(result, HttpStatus.CREATED)
        } else {
            ResponseEntity<Any>(dto, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/list")
    fun list(principal: Principal): ResponseEntity<Any> =
        ResponseEntity<Any>(toDoService.findToDoListByUser(principal.name), HttpStatus.OK)


    @DeleteMapping
    fun delete(@RequestBody dto: ToDoDto, principal: Principal): ResponseEntity<Any> {
        logger.warn("Name dto = ${dto.username}  namePrincipal = ${principal.name}")
        if (dto.username == null) dto.username = principal.name
        if (dto.username != principal.name) {
            return ResponseEntity<Any>(dto, HttpStatus.FORBIDDEN)
        }
        return ResponseEntity<Any>(toDoService.deleteSelected(dto), HttpStatus.OK)
    }

    @PatchMapping
    fun update(@RequestBody dto: ToDoDto, principal: Principal): ResponseEntity<Any> {
        logger.info("Update todo = $dto  ${dto.id.toString()}")
        return if (dto.username != principal.name) {
            //ResponseStatusException(HttpStatus.FORBIDDEN)
            ResponseEntity<Any>(dto, HttpStatus.FORBIDDEN)
        } else if (dto.id == null) {
            ResponseEntity<Any>(dto, HttpStatus.NOT_FOUND)
        } else {
            val result = toDoService.updateSelected(dto)
            if (result == null) ResponseEntity<Any>(dto, HttpStatus.NOT_FOUND)
            else ResponseEntity<Any>(result, HttpStatus.OK)
        }
    }

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/todo/hc - OK"
    }

}