package com.scimbosh.simplespringbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,

    @Column(unique=true)
    private var username: String? = null,

    private var password: String? = null,

    private var roles: String? = null,
) {
    
    constructor(username: String?, password: String?, roles: String?) : this() {
        this.username = username
        this.password = password
        this.roles = roles
    }

    override fun toString(): String {
        return "SecurityUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}'
    }

    fun getId(): Long {
        return id!!
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getUsername(): String {
        return username!!
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getPassword(): String {
        return password!!
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getRoles(): String {
        return roles!!
    }

    fun setRoles(roles: String?) {
        this.roles = roles
    }

}
