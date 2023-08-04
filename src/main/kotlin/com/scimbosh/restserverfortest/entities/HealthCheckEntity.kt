package com.scimbosh.restserverfortest.entities

import jakarta.persistence.*

@Entity
@Table(name = "health")
class HealthCheckEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,
    var request: String? = "",
)