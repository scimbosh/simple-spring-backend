package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.SecurityUser
import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["/auth"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
    private val userService: UserService
) {

    @GetMapping("/login")
    fun login(user: Principal): Principal {
        return user
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: UserDto): ResponseEntity<Any> {
        val result = userService.createUser(user)
        return if (result == null ) ResponseEntity<Any>(user, HttpStatus.BAD_REQUEST)
        else ResponseEntity<Any>(user, HttpStatus.CREATED)
    }

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/auth/hc - OK"
    }
}

