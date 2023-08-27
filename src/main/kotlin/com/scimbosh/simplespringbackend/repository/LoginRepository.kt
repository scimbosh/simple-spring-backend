package com.scimbosh.simplespringbackend.repository

import com.scimbosh.simplespringbackend.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface LoginRepository: CrudRepository<UserEntity, Int> {
    fun findByLogin(login: String): UserEntity?
}