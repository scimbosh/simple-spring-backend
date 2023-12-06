package com.scimbosh.simplespringbackend.configure

import org.springframework.boot.context.properties.ConfigurationProperties

//@ConstructorBinding
@ConfigurationProperties(prefix = "app-config")
class PropertiesConfig (
    val helloMessage: String,
    val frontendUrl: String
)