package com.scimbosh.restserverfortest.entities

import jakarta.persistence.*
import org.springframework.web.bind.annotation.RequestMethod
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "health")
class HealthCheckEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,
    var request: String? = "",
) {
}