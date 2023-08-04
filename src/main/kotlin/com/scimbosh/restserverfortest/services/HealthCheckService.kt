package com.scimbosh.restserverfortest.services

import com.scimbosh.restserverfortest.dto.HealthCheckDto
import com.scimbosh.restserverfortest.entities.HealthCheckEntity

interface HealthCheckService {

    fun create(dto: HealthCheckDto): HealthCheckEntity

    fun getAll(pageIndex: Int?): List<HealthCheckDto>

    fun getById(id: Int): HealthCheckDto?

    fun deleteById(id: Int): Boolean
}