package com.scimbosh.simplespringbackend.entities

import jakarta.persistence.*

@Entity
@Table(name = "todos")
class ToDoEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE)
    //@GeneratedValue(strategy=GenerationType.TABLE)
//    @Column(name="id", nullable=false, updatable=false)
    @Column(name="id")
    val id: Int? = 0,
    @Column(name="user_id")
    val userId: Int = 0,
    @Column(name="content")
    val content: String = "",

)