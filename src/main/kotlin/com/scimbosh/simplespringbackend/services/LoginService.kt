package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.UserDto

interface LoginService{

    fun create(dto: UserDto): UserDto?

    fun generateToken(dto: UserDto): UserDto?

}