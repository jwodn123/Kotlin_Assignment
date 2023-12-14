//package com.teamsparta.assignment.config
//
//import com.teamsparta.assignment.jwt.JwtUtil
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.builders.WebSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.cors.CorsConfiguration
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//class WebSecurityConfig @Autowired constructor(private val jwtUtil: JwtUtil) {
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val config = CorsConfiguration()
//        config.addAllowedOrigin("http://localhost:3000")
//        config.addAllowedOrigin("http://tube-project.com")
//        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER)
//        config.addAllowedMethod("*")
//        config.addAllowedHeader("*")
//        config.allowCredentials = true
//
//        val source = UrlBasedCorsConfigurationSource()
//        source.registerCorsConfiguration("/**", config)
//        return source
//    }
//
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http.cors()
//            .and()
//            .csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permit pre-flight requests
//            .antMatchers("/users/**").permitAll()
//            .antMatchers("/rooms/").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .addFilterBefore(JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
//
//        return http.build()
//    }
//}
