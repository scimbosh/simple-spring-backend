package com.scimbosh.simplespringbackend.controlers

import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
//@RequestMapping(value = ["/login"])
@RequestMapping(value = ["/auth"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
    //private val toDoService: ToDoService
) {

    @GetMapping("/login")
    fun login(user: Principal): Principal {
        return user
    }

//    @PostMapping("/add")
//    fun addItem(@RequestBody dto: ToDoDto): Any {
//        return if (toDoService.saveToDo(dto) != null) {
//            dto
//        } else {
//            BodyContent(isSuccessful = false, obj = dto)
//        }
//    }


    @GetMapping("/hc")
    fun userIndex(): String {
        return "/auth/hc - OK"
    }
}

