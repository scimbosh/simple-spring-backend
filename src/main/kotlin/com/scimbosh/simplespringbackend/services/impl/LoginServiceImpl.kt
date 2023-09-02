package com.scimbosh.simplespringbackend.services.impl

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.LoginRepository
import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class LoginServiceImpl(
    private val loginRepo: LoginRepository
) : LoginService {

    @Transactional
    override fun create(dto: UserDto): UserDto? {
        return if (loginRepo.findByLogin(dto.login).isEmpty()) {
            loginRepo.save(dto.toEntity()).toDto()
        }else{
            null
        }
    }

    @Modifying
    override fun generateToken(dto: UserDto): UserDto? {
        val user = getUserByLoginAndPass(dto)
        return if (user != null) {
            user.token = UUID.randomUUID().toString()
            return loginRepo.save(user).toDto()
        } else {
            null
        }
    }
    private fun getUserByLoginAndPass(dto: UserDto): UserEntity? =
        loginRepo.findByLogin(dto.login).find { it?.password == dto.password }


    override fun checkCredentials(dto: UserDto): UserDto? {
        val user = getUserByLoginAndPass(dto)?.toDto()
        return if (user?.token == dto.token) {
            user
        }
        else {
            null
        }
    }


    private fun UserEntity.toDto(): UserDto =
        UserDto(
            id = this.id,
            login = this.login,
            password = this.password,
            token = this.token
        )

    private fun UserDto.toEntity(): UserEntity =
        UserEntity(
            id = 0,
            login = this.login,
            password = this.password,
            token = this.token
        )

}