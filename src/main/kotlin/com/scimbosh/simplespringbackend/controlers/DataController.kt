package com.scimbosh.simplespringbackend.controlers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = ["/data"])
class DataController(
) {
    @PostMapping
    //fun getData(@RequestBody dto: UserDto): Any {
    fun getData(): Any {

           return ResponseStatusException(HttpStatus.UNAUTHORIZED)


    }
}