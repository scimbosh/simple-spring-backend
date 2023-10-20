package com.scimbosh.simplespringbackend.controlers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView


@RestController
class RedirectController {

    @GetMapping("/redirect")
    fun redirectWithUsingRedirectView(): RedirectView {
        return RedirectView("http://localhost:4200/data")
    }
}