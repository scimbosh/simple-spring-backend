package com.scimbosh.simplespringbackend.dto

data class ToDoDto(
    var id: Long? = null,
    var userId: Long? = 0,
    var username: String?,
    var content: String?,
    var checked: Boolean?
)