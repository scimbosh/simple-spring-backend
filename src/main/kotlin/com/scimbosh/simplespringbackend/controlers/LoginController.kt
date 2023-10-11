package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
//@RequestMapping(value = ["/login"])
//@RequestMapping(value = ["/api"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
    private  val loginService: LoginService
) {

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/login2")
    fun login2(user: Principal): Principal {
        return user
    }

//    @GetMapping("/")
//    fun index(): String {
//        return "index"
//    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }

//    @PostMapping("/signup")
//    @CrossOrigin(origins = ["http://localhost:4200"])
//    fun signUp(@RequestBody dto: UserDto): Any {
//        val user = loginService.create(dto)
//        return if (user != null) {
//            ResponseEntity.status(201).body(user)
//        }else{
//            ResponseStatusException(HttpStatus.CONFLICT, "Login is busy")
//        }
//    }
//
//    @PostMapping("/signin")
//    @CrossOrigin(origins = ["http://localhost:4200"])
//    fun signIn(@RequestBody dto: UserDto): Any {
//        val checkResult = loginService.generateToken(dto)
//        return if (checkResult != null ){
//            ResponseEntity.ok(checkResult)
//        }else{
//            ResponseEntity.status(401).body(BodyContent(isSuccessful = false, obj = dto))
//        }
//    }


}

