package com.scimbosh.simplespringbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@SpringBootApplication
class SimpleSpringBackend

fun main(args: Array<String>) {
	runApplication<SimpleSpringBackend>(*args){
		addInitializers( beans {
			bean {
				fun user(user: String, password: String, vararg  roles: String) =
					User
						.withDefaultPasswordEncoder()
						.username(user)
						.password(password)
						.roles(*roles)
						.build()

				InMemoryUserDetailsManager(user("user", "password", "USER")
					, user("admin", "password", "USER", "ADMIN"))
			}
		})
	}
}