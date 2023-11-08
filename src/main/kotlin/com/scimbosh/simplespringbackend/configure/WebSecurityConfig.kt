package com.scimbosh.simplespringbackend.configure

import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.UserRepository
import com.scimbosh.simplespringbackend.services.JpaUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
//    @Value("\${app-config.hello-message}")  private val helloMessage: String,
    private val properties: PropertiesConfig
) {

    @Autowired
    lateinit var userDetailsService: JpaUserDetailsService

    @Autowired
    fun configAuthentication(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
        //return NoOpPasswordEncoder.getInstance() //      if you don't need encryption password in DB
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors { cors -> cors.disable() } // not working
            .csrf { csrf -> csrf.disable() }.authorizeRequests()
            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            .requestMatchers("/actuator/**").permitAll().requestMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()  // disable cors error to options request
            .requestMatchers("/user/login", "/user/create", "/user/roles", "/user/hc").permitAll()
            .requestMatchers(HttpMethod.GET, "/user/list").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.PATCH, "/user").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/user").hasAuthority("ROLE_ADMIN").anyRequest().authenticated().and()
            .formLogin().loginPage("/user/login").and().httpBasic()
        return http.build()
    }

    @Bean
    fun commandLineRunner(userRepository: UserRepository, encoder: PasswordEncoder): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            println("Execute task")
            println(properties.helloMessage)
            if (userRepository.findByUsername("admin") == null) {
                println("Create ADMIN")
                userRepository.save(
                    UserEntity(
                        "admin", encoder.encode("password"), listOf("ROLE_USER", "ROLE_ADMIN")
                    )
                )
            }
            if (userRepository.findByUsername("user") == null) {
                println("Create USER")
                userRepository.save(UserEntity("user", encoder.encode("password"), listOf("ROLE_USER")))
            }
        }
    }

}