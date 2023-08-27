package com.scimbosh.simplespringbackend.repository

import com.scimbosh.simplespringbackend.entities.HealthCheckEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface HealthCheckRepository: CrudRepository<HealthCheckEntity, Int> {
    fun findByOrderById(pageable: Pageable): List<HealthCheckEntity>
}