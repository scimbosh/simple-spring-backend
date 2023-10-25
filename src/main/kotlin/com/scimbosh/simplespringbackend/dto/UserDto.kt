package com.scimbosh.simplespringbackend.dto

data class UserDto(
    var id: Long?,
    var username: String?,
    var password: String?,
    var roles: List<String>?
)
