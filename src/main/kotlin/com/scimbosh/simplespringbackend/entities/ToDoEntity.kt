package com.scimbosh.simplespringbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "todos")
class ToDoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE)
    //@GeneratedValue(strategy=GenerationType.TABLE)
//    @Column(name="id", nullable=false, updatable=false)
    @Column(name="id")
    val id: Long? = 0,
    @Column(name="username")
    val username: String? = "",
    @Column(name="content")
    val content: String? = "",
    @Column(name="checked")
    val checked: Boolean? = false

)