package com.scimbosh.simplespringbackend.dto

data class ToDoDto(
    var id: Long? = null,
    var username: String?,
    var content: String?,
    var checked: Boolean?
    //var time: Instant
)