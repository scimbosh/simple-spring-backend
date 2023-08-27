package com.scimbosh.simplespringbackend.exception

data class ApiError(
    val errorCode: String,
    val description: String,
)