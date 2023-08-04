package com.scimbosh.restserverfortest.controlers

import com.scimbosh.restserverfortest.dto.HealthCheckDto
import com.scimbosh.restserverfortest.entities.HealthCheckEntity
import com.scimbosh.restserverfortest.model.SuccessBodyContent
import com.scimbosh.restserverfortest.services.HealthCheckService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/hc"])
class HealthCheckController(
    private val healthCheckService: HealthCheckService
) {

    @GetMapping
    fun check(): ResponseEntity<Any>{
        return  ResponseEntity.ok("Health Check - OK")
    }

    @GetMapping("/endpoint")
    fun endpoint(): ResponseEntity<Any>{
        return ResponseEntity.ok("Health Check Endpoint - OK")
    }

    @PostMapping("/create")
    fun createCheck(@RequestBody dto: HealthCheckDto): HealthCheckEntity {
        dto.request = UUID.randomUUID().toString()
        return healthCheckService.create(dto)
    }

    @GetMapping("/all")
    fun findAll(@RequestParam("page") pageIndex: Int?): List<HealthCheckDto> =
        healthCheckService.getAll(pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Any> {
        val event = healthCheckService.getById(id)
        return if(event != null){
            ResponseEntity.ok(event)
        }else{
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id: Int): ResponseEntity<Any> {
        val event = healthCheckService.getById(id)
        return if(event != null){
            if (healthCheckService.deleteById(id)) ResponseEntity.ok(SuccessBodyContent())
            else ResponseEntity.internalServerError().build()
        }else{
            ResponseEntity.notFound().build()
        }
    }

}