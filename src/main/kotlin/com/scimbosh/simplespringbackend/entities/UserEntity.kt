package com.scimbosh.simplespringbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    val id: Int? = 0,
    @Column(name="login")
    var login: String = "",
    var password: String = "",
    //var token: String? = ""
)