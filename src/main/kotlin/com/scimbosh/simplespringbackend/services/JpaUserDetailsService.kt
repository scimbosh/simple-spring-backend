package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.SecurityUser
import com.scimbosh.simplespringbackend.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class JpaUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? =
        userRepository.findByUsername(username)?.let { SecurityUser(it) }

}