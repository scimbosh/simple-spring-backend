package com.scimbosh.restserverfortest.services.Impl

import com.scimbosh.restserverfortest.dto.HealthCheckDto
import com.scimbosh.restserverfortest.entities.HealthCheckEntity
import com.scimbosh.restserverfortest.repository.HealthCheckRepository
import com.scimbosh.restserverfortest.services.HealthCheckService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HealthCheckServiceImpl(
    private val hcRepo: HealthCheckRepository
): HealthCheckService {

    @Transactional
    override fun create(dto: HealthCheckDto): Int {
        val healthCheckEntity = hcRepo.save(dto.toEntity())
        if  (healthCheckEntity.id != null) {
            return healthCheckEntity.id
        }
        return -1
    }

    override fun getAll(pageIndex: Int?): List<HealthCheckDto> {
        val result: List<HealthCheckDto> = if(pageIndex != null){
            hcRepo.findByOrderById(PageRequest.of(pageIndex, 5)).map { it.toDto()}
        }else{
            hcRepo.findAll().map { it.toDto()}
        }
        return result
    }

    override fun getById(id: Int): HealthCheckDto? =
        hcRepo.findByIdOrNull(id)
            ?.toDto()


    private fun HealthCheckDto.toEntity(): HealthCheckEntity =
        HealthCheckEntity(
            id = 0,
            request = this.request
        )

    private fun HealthCheckEntity.toDto(): HealthCheckDto =
        HealthCheckDto(
            id = this.id,
            request = this.request
        )
}