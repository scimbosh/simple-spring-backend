package com.scimbosh.simplespringbackend

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
@OpenAPIDefinition
@ConfigurationPropertiesScan
//@EntityScan("com.scimbosh.simplespringbackend.entities")
class SimpleSpringBackend

fun main(args: Array<String>) {
	runApplication<SimpleSpringBackend>(*args)
}


