package com.scimbosh.restserverfortest.repository

import com.scimbosh.restserverfortest.entities.HealthCheckEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface HealthCheckRepository: CrudRepository<HealthCheckEntity, Int> {
    fun findByOrderById(pageable: Pageable): List<HealthCheckEntity>
}