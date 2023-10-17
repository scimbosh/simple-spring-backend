package com.scimbosh.simplespringbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
//@EntityScan("com.scimbosh.simplespringbackend.entities")
class SimpleSpringBackend

fun main(args: Array<String>) {
	runApplication<SimpleSpringBackend>(*args)
}


