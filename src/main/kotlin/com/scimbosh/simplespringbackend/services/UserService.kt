package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.exception.MatchException
import com.scimbosh.simplespringbackend.exception.NotFoundException
import com.scimbosh.simplespringbackend.exception.UniquenessViolationException
import com.scimbosh.simplespringbackend.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.Principal
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createUser(dto: UserDto): UserDto? {
        dto.password = passwordEncoder.encode(dto.password)
        logger.info("dto.password = ${dto.password}")
        return try {
            userRepository.save(dto.toEntity()).toDto()
        } catch (e: Exception) {
            throw UniquenessViolationException(cause = dto.toString(), message = e.message)
        }

    }

    @Transactional
    fun updatePassword(principal: Principal, newPassword: String, currentPassword: String): UserDto? {
        val passwordInSession = userRepository.findByUsername(principal.name)?.getPassword()
        logger.info("newPassword = $newPassword")
        logger.info("currentPassword = $currentPassword")
        logger.info("passwordInSession = $passwordInSession ")

        return if (passwordEncoder.matches(currentPassword, passwordInSession)) {
            val userEntity: UserEntity = userRepository.findByUsername(principal.name)!!
            userEntity.setPassword(passwordEncoder.encode(newPassword))
            userRepository.save(userEntity).toDto()
        } else {
            throw MatchException(cause = currentPassword)
        }
    }

    @Transactional
    fun updateUser(user: UserDto): UserDto {
        val currentUserEntity: UserEntity? = userRepository.findById(user.id!!).getOrNull()
        if (currentUserEntity != null) {
            if (!user.username.isNullOrEmpty()) currentUserEntity.setUsername(user.username)
            if (!user.roles.isNullOrEmpty()) currentUserEntity.setRoles(user.roles)
            if (!user.password.isNullOrEmpty()) currentUserEntity.setPassword(passwordEncoder.encode(user.password))
            return userRepository.save(currentUserEntity).toDto()
        } else {
            throw NotFoundException(cause = user.id.toString(), message = "user not found")
        }
    }

    @Transactional
    fun deleteUser(user: UserDto) {
        if (user.id != null) userRepository.deleteById(user.id!!)
        else throw NotFoundException(cause = user.toString(), message = "user.id not found")
    }

    fun getUsers(): List<UserDto>? =
        userRepository.findAll().map {
            it.setPassword("")
            it.toDto()
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