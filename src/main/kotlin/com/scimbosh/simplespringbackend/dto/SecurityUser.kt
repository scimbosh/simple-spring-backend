package com.scimbosh.simplespringbackend.dto

import com.scimbosh.simplespringbackend.entities.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(user: UserEntity) : UserDetails {

    private val user: UserEntity

    init {
        this.user = user
    }

    override fun getUsername(): String {
        return user.getUsername()

    }

    override fun getPassword(): String {
        return user.getPassword()
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return user
            .getRoles()
            ?.map { role: String? -> SimpleGrantedAuthority(role) }
            ?.toList()!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}