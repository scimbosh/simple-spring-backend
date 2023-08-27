package com.scimbosh.simplespringbackend.dto

data class UserDto (
    val id: Int? = null,
    var login: String,
    var password: String,
    //var token: String? = null
)