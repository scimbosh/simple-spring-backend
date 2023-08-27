package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity

interface LoginService{

    fun create(dto: UserDto): UserEntity

}