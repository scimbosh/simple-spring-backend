package com.scimbosh.simplespringbackend.configure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.*
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain  {
        http
            //.cors { cors -> cors.disable() }
            //.cors { cors: CorsSpec -> cors.disable() }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests(
                Customizer { requests ->
                    requests
                        .requestMatchers("/", "/home", "/hc", "/api/signup", "/api/signin").permitAll()
                        .anyRequest().authenticated()
                }
            )
            .formLogin { form: FormLoginConfigurer<HttpSecurity?> ->
                form
                    //.loginPage("/api/signin") // ПОПРОБОВАТЬ РЕАЛИЗОВАТЬ РЕАЛЬНЫЙ ЛОГИН И ПОСЛЕ ПОСМОТРЕТЬ БУДЕТ ЛИ ОШИБКА КОРС
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/redirect")
            }
            .logout { logout: LogoutConfigurer<HttpSecurity?> -> logout.permitAll() }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

//    @Bean
//    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        http
//            .cors { cors: ServerHttpSecurity.CorsSpec -> cors.disable() }
//        return http.build()
//    }

}