package com.scimbosh.simplespringbackend.dto

data class UserDto (
    val id: Int? = null,
    var login: String,
    var password: String,
    //var token: String? = null
)
{
    override fun toString(): String {
        return "{'user': { 'id': '$id', 'login': '$login', 'password': '$password'}}"
    }
}