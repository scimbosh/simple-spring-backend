package com.scimbosh.restserverfortest.controlers

import com.scimbosh.restserverfortest.dto.HealthCheckDto
import com.scimbosh.restserverfortest.services.HealthCheckService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun createCheck(@RequestBody dto: HealthCheckDto): Int {
        dto.request = "POST"
        return healthCheckService.create(dto)
    }

    @GetMapping("/all")
    fun findAll(@RequestParam("page") pageIndex: Int?): List<HealthCheckDto> =
        healthCheckService.getAll(pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Any> {
//        val event = healthCheckService.getById(id)
//        return if(event != null){
//            ResponseEntity.ok(event)
//        }else{
//            ResponseEntity.notFound().build()
//        }

        return try{
            ResponseEntity.ok(healthCheckService.getById(id))
        }catch (e: Exception){
            ResponseEntity.notFound().build()
        }
    }


}