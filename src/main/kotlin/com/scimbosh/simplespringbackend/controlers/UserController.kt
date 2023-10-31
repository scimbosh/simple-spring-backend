package com.scimbosh.simplespringbackend.controlers

import com.fasterxml.jackson.databind.JsonNode
import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.services.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["/user"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
open class UserController(
    private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/hc")
    fun userIndex(): String = "/auth/hc - OK"

    @GetMapping("/login")
    fun login(user: Principal): Principal = user

    @GetMapping("/roles")
    fun getRoles(): List<String> = listOf("ROLE_USER","ROLE_ADMIN")

    @GetMapping("/list")
    fun getUsers(): List<UserDto>? = userService.getUsers()

    @PostMapping("/create")
    fun createUser(@RequestBody user: UserDto): ResponseEntity<Any> =
        ResponseEntity<Any>(userService.createUser(user), HttpStatus.CREATED)

    @PatchMapping("/password")
    fun updatePassword(
        principal: Principal,
        @RequestBody jsonNode: JsonNode
    ): ResponseEntity<Any> {
        val newPassword = jsonNode.get("newPassword").asText()
        val currentPassword = jsonNode.get("currentPassword").asText()
        return if (userService.updatePassword(principal, newPassword, currentPassword) != null ) ResponseEntity<Any>(HttpStatus.OK)
        else  ResponseEntity<Any>(HttpStatus.FORBIDDEN)
    }

    @PatchMapping()
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PostAuthorize("hasRole('ROLE_ADMIN')")
    open fun updateUser(@RequestBody user: UserDto): UserDto = userService.updateUser(user)

    @DeleteMapping()
    fun deleteUser(@RequestBody user: UserDto){
        userService.deleteUser(user)
    }


}

