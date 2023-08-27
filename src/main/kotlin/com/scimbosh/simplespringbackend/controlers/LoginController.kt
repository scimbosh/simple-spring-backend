package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/login"])
class LoginController(
    private  val loginService: LoginService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody dto: UserDto): UserEntity {
        return loginService.create(dto)
    }
//    @PostMapping("/signin")
//    fun signIn(@RequestBody dto: UserDto): UserEntity {
//        loginService.checkCredentials(dto)
//        return UserEntity()
//    }


}

