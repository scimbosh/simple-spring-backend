package com.scimbosh.simplespringbackend.configure

import com.scimbosh.simplespringbackend.repository.UserRepository
import com.scimbosh.simplespringbackend.services.JpaUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig() {

    @Autowired
    lateinit var userDetailsService: JpaUserDetailsService

    @Autowired
    fun configAuthentication(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { cors -> cors.disable() } // not working
            .csrf { csrf -> csrf.disable() }
            .authorizeRequests()
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // disable cors error to options request
            .requestMatchers("/user/login", "/user/create", "/user/hc").permitAll()
            //.requestMatchers("/user/index").hasAuthority("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/user/login")
            .and().httpBasic()
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

//if you don't need encryption password in DB
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return NoOpPasswordEncoder.getInstance()
//    }


    //example of running custom code after application launch
    @EventListener
    fun doSomethingAfterStartup(event: ApplicationReadyEvent) {
        println("hello world, I have just started up")
    }

    //uncomment if you need to save users in the DB
    @Bean
    fun commandLineRunner(users: UserRepository, encoder: PasswordEncoder): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            println ("EXECUTE START")
            //users.save(UserEntity("user", encoder.encode("password"), "ROLE_USER"))
           // users.save(UserEntity("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"))
            println ("EXECUTE END")
        }
    }


}