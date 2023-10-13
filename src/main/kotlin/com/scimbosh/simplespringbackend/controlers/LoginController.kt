package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.LoginService
import com.scimbosh.simplespringbackend.services.ToDoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import org.springframework.http.MediaType;


@RestController
//@RequestMapping(value = ["/login"])
//@RequestMapping(value = ["/api"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
    private val loginService: LoginService,
    private val toDoService: ToDoService
) {


    @GetMapping("/login2")
    fun login2(user: Principal): Principal {
        return user
    }

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


    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }
}

