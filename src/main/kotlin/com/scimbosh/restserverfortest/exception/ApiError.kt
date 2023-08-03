package com.scimbosh.restserverfortest.exception

data class ApiError(
    val errorCode: String,
    val description: String,
)