package com.scimbosh.restserverfortest.services

import com.scimbosh.restserverfortest.dto.HealthCheckDto

interface HealthCheckService {

    fun create(dto: HealthCheckDto): Int

    fun getAll(pageIndex: Int?): List<HealthCheckDto>

    fun getById(id: Int): HealthCheckDto
}