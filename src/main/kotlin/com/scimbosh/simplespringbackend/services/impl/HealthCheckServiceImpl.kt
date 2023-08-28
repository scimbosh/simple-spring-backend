package com.scimbosh.simplespringbackend.services.impl

import com.scimbosh.simplespringbackend.dto.HealthCheckDto
import com.scimbosh.simplespringbackend.entities.HealthCheckEntity
import com.scimbosh.simplespringbackend.repository.HealthCheckRepository
import com.scimbosh.simplespringbackend.services.HealthCheckService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HealthCheckServiceImpl(
    private val hcRepo: HealthCheckRepository
): HealthCheckService {

    @Transactional
    override fun create(dto: HealthCheckDto): HealthCheckEntity {
        return hcRepo.save(dto.toEntity())
    }

    override fun getAll(pageIndex: Int?): List<HealthCheckDto> {
        val result: List<HealthCheckDto> = if(pageIndex != null){
            hcRepo.findByOrderById(PageRequest.of(pageIndex, 5)).map { it.toDto()}
        }else{
            hcRepo.findAll().map { it.toDto()}
        }
        return result
    }

    override fun deleteById(id: Int): Boolean {
        hcRepo.deleteById(id)
        println ("result.isEmpty -> ${hcRepo.findById(id).isEmpty} ")
        return hcRepo.findById(id).isEmpty
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