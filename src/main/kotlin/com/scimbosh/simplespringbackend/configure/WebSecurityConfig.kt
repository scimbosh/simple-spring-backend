package com.scimbosh.simplespringbackend.configure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class WebSecurityConfig {


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {


        http.authorizeRequests()
            .requestMatchers("/login").permitAll()
            //.requestMatchers("/user/index").hasAuthority("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login")
            .and().httpBasic()
        return http.build()
    }


//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val configuration = CorsConfiguration()
//        configuration.allowedOrigins = listOf("https://localhost", "http://localhost",  "http://127.0.0.1", "http://[::1]")
//        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", configuration)
//        return source
//    }

//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        val userDetails = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build()
//        return InMemoryUserDetailsManager(userDetails)
//    }

//    @Bean
//    fun userDetailsService(): UserDetailsService {
//        return InMemoryUserDetailsManager().apply {
//            createUser(
//                User.withUsername("user")
//                    .password(passwordEncoder().encode("password"))
//                    .roles("USER")
//                    .build()
//            )
//        }
//    }
//
//    @Bean
//    fun passwordEncoder() = BCryptPasswordEncoder()

////////////////////////////////////////////////БЫЛО 2

//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeRequests()
//            .requestMatchers("/login").permitAll()
//            //.requestMatchers("/user/index").hasAuthority("USER")
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/login")
//        return http.build()
//    }


//////////////////////////////////////////////// БЫЛО 1

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


/// TEMP


    //    @Configuration
//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    protected class SecurityConfiguration : WebSecurityConfigurerAdapter() {
//        @Throws(java.lang.Exception::class)
//        protected fun configure(http: HttpSecurity) {
//            // @formatter:off
//            http
//                .formLogin().loginPage("/login").successForwardUrl("/user").and()
//                .logout().and()
//                .authorizeRequests()
//                .antMatchers("/index.html", "/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            // @formatter:on
//        }
//    }

}