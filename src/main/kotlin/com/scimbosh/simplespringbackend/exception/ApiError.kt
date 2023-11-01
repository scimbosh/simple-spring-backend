package com.scimbosh.simplespringbackend.exception

data class ApiError(
    val errorCode: String?,
    val cause: String?,
    val message: String?
)