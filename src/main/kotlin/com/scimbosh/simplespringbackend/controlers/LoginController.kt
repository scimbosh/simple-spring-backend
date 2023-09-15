package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
//@RequestMapping(value = ["/login"])
@RequestMapping(value = ["/api"])
@CrossOrigin(origins = ["http://localhost:4200"])
class LoginController(
    private  val loginService: LoginService
) {

    @PostMapping("/signup")
    @CrossOrigin(origins = ["http://localhost:4200"])
    fun signUp(@RequestBody dto: UserDto): Any {
        val user = loginService.create(dto)
        return if (user != null) {
            ResponseEntity.status(201).body(user)
        }else{
            ResponseStatusException(HttpStatus.CONFLICT, "Login is busy")
        }
    }

    @PostMapping("/signin")
    @CrossOrigin(origins = ["http://localhost:4200"])
    fun signIn(@RequestBody dto: UserDto): Any {
        val checkResult = loginService.generateToken(dto)
        return if (checkResult != null ){
            ResponseEntity.ok(checkResult)
        }else{
            ResponseEntity.status(401).body(BodyContent(isSuccessful = false, obj = dto))
        }
    }


}

