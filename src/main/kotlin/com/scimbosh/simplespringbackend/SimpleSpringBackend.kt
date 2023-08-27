package com.scimbosh.simplespringbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleSpringBackend

fun main(args: Array<String>) {
	runApplication<SimpleSpringBackend>(*args)
}
