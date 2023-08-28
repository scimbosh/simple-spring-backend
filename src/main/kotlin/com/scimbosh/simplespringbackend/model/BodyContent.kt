package com.scimbosh.simplespringbackend.model

import com.scimbosh.simplespringbackend.dto.UserDto

class BodyContent(
    val isSuccessful: Boolean = true,
    val message: String? = null,
    val user: UserDto? = null
)