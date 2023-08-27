package com.scimbosh.simplespringbackend.services

import com.scimbosh.simplespringbackend.dto.HealthCheckDto
import com.scimbosh.simplespringbackend.entities.HealthCheckEntity

interface HealthCheckService {

    fun create(dto: HealthCheckDto): HealthCheckEntity

    fun getAll(pageIndex: Int?): List<HealthCheckDto>

    fun getById(id: Int): HealthCheckDto?

    fun deleteById(id: Int): Boolean
}