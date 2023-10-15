package com.scimbosh.simplespringbackend.configure

import com.scimbosh.simplespringbackend.entities.UserEntity
import com.scimbosh.simplespringbackend.repository.UserRepository
import com.scimbosh.simplespringbackend.services.JpaUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
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
            .csrf { csrf -> csrf.disable() }
            .authorizeRequests()
            .requestMatchers("/login2").permitAll()
            //.requestMatchers("/user/index").hasAuthority("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login2")
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
    @Bean
    fun commandLineRunner(users: UserRepository, encoder: PasswordEncoder): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            println ("EXECUTE START")
            users.save(UserEntity("user", encoder.encode("password"), "ROLE_USER"))
            users.save(UserEntity("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"))
            println ("EXECUTE END")
        }
    }



////////////////////////////////////////////////  It was

//    @Bean
//    @Throws(Exception::class)
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain  {
//        http
//            //.cors { cors -> cors.disable() }
//            //.cors { cors: CorsSpec -> cors.disable() }
//            .csrf { csrf -> csrf.disable() }
//            .authorizeHttpRequests(
//                Customizer { requests ->
//                    requests
//                        .requestMatchers("/", "/home", "/hc", "/api/signup", "/api/signin").permitAll()
//                        .anyRequest().authenticated()
//                }
//            )
//            .formLogin { form: FormLoginConfigurer<HttpSecurity?> ->
//                form
//                    //.loginPage("/api/signin") // ПОПРОБОВАТЬ РЕАЛИЗОВАТЬ РЕАЛЬНЫЙ ЛОГИН И ПОСЛЕ ПОСМОТРЕТЬ БУДЕТ ЛИ ОШИБКА КОРС
//                    .loginPage("/login")
//                    .permitAll()
//                    .defaultSuccessUrl("/redirect")
//            }
//            .logout { logout: LogoutConfigurer<HttpSecurity?> -> logout.permitAll() }
//        return http.build()
//    }

//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val user: UserDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(user)
//    }

//    @Bean
//    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
//        http
//            .cors { cors: ServerHttpSecurity.CorsSpec -> cors.disable() }
//        return http.build()
//    }



}