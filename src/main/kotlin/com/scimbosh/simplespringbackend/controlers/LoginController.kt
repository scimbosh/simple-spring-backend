package com.scimbosh.simplespringbackend.controlers

import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["/auth"])
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 86400)
class LoginController(
) {

    @GetMapping("/login")
    fun login(user: Principal): Principal {
        return user
    }

    @GetMapping("/hc")
    fun userIndex(): String {
        return "/auth/hc - OK"
    }
}

