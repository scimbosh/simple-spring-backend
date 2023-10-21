package com.scimbosh.simplespringbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "todos")
class ToDoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="id", nullable=false, updatable=false)
    @Column(name="id")
    val id: Long? = 0,

    @Column(name="username", nullable=false)
    val username: String? = "",

    @Column(name="content")
    var content: String? = "",

    @Column(name="checked")
    var checked: Boolean? = false

)