package com.scimbosh.restserverfortest.dto

import org.springframework.web.bind.annotation.RequestMethod
import java.time.LocalDateTime

data class HealthCheckDto(
    val id: Int? = null,
    var request: String?
) {
}