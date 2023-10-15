package com.scimbosh.simplespringbackend.controlers

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.model.BodyContent
import com.scimbosh.simplespringbackend.services.ToDoService
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
//@RequestMapping(value = ["/login"])
//@RequestMapping(value = ["/api"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
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

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }
}

