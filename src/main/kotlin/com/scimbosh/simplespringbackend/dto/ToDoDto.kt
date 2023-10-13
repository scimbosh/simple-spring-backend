package com.scimbosh.simplespringbackend.dto

data class ToDoDto (
    var id: Int? = null,
    var userId: Int,
    var content: String,
    //var time: Instant
)