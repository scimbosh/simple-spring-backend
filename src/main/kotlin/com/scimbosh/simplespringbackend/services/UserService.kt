package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.ToDoDto
import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.ToDoEntity
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun createUser(dto: UserDto): UserDto? {
        return userRepository.save(dto.toEntity()).toDto()
    }

    private fun UserDto.toEntity(): UserEntity =
        UserEntity(
            username = this.username,
            password = this.password,
            roles = this.roles
        )

    private fun UserEntity.toDto(): UserDto =
        UserDto(
            id = getId(),
            username = getUsername(),
            password = getPassword(),
            roles = getRoles()
        )

}