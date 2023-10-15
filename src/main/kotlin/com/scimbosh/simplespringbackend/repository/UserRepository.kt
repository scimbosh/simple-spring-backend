package com.scimbosh.simplespringbackend.repository

import com.scimbosh.simplespringbackend.entities.UserEntity
import org.springframework.data.repository.CrudRepository


interface UserRepository: CrudRepository<UserEntity, Long> {
    fun findByUsername(username: String?): UserEntity?
}