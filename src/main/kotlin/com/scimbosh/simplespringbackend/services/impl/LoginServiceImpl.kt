package com.scimbosh.simplespringbackend.services.impl

import com.scimbosh.simplespringbackend.dto.UserDto
import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.LoginRepository
import com.scimbosh.simplespringbackend.services.LoginService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginServiceImpl(
    private val loginRepo: LoginRepository
) : LoginService {

    @Transactional
    override fun create(dto: UserDto): UserDto? {
        return if (getByLogin(dto.login).isEmpty()) {
            loginRepo.save(dto.toEntity()).toDto()
        }else{
            null
        }
    }

    fun getByLogin(login: String): List<UserDto?> {
        val users = loginRepo.findByLogin(login)
        return users.map { it?.toDto() }
    }

    //fun checkCredentials(dto: UserDto): Boolean = getByLogin(dto.login) != null



    private fun UserEntity.toDto(): UserDto =
        UserDto(
            id = this.id,
            login = this.login,
            password = this.password,
            //token = this.token
        )

    private fun UserDto.toEntity(): UserEntity =
        UserEntity(
            id = 0,
            login = this.login,
            password = this.password,
            //token = this.token
        )

}