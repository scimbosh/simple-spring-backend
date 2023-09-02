package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.SecretDto
import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = ["/data"])
class DataController(
    private  val loginService: LoginService
) {
    @PostMapping
    fun getData(@RequestBody dto: UserDto): Any {
        return if (loginService.checkCredentials(dto) != null) {
            ResponseEntity.ok(SecretDto(secret="secret user data"))
        }else{
            ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }

    }
}