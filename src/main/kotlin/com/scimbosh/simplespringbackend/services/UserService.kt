package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.Principal

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Transactional
    fun createUser(dto: UserDto): UserDto? {
        dto.password = passwordEncoder.encode(dto.password)
        logger.warn("dto.password = ${dto.password}")
        return userRepository.save(dto.toEntity()).toDto()
    }

    @Transactional
    fun updatePassword(principal: Principal, newPassword: String, currentPassword: String): UserDto? {
        val passwordInSession = userRepository.findByUsername(principal.name)?.getPassword()
        logger.warn("newPassword = $newPassword")
        logger.warn("currentPassword = $currentPassword")
        logger.warn("passwordInSession = $passwordInSession ")

        return if (passwordEncoder.matches(currentPassword, passwordInSession)) {
            val userEntity: UserEntity = userRepository.findByUsername(principal.name)!!
            userEntity.setPassword(passwordEncoder.encode(newPassword))
            userRepository.save(userEntity).toDto()
        } else {
            null
        }
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