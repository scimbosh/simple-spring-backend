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
class UserController(
    private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    @GetMapping("/login")
    fun login(user: Principal): Principal {
        return user
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: UserDto): ResponseEntity<Any> {
        return if (userService.createUser(user) == null) ResponseEntity<Any>(user, HttpStatus.BAD_REQUEST)
        else ResponseEntity<Any>(user, HttpStatus.CREATED)
    }

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

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/auth/hc - OK"
    }
}

