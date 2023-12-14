//package com.teamsparta.assignment.domain.jwt
//
//import lombok.extern.slf4j.Slf4j
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpStatus
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.web.filter.OncePerRequestFilter
//import java.io.IOException
//import javax.servlet.FilterChain
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//@Slf4j
//class JwtAuthFilter @Autowired constructor(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
//
//    @Throws(ServletException::class, IOException::class)
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val token = jwtUtil.resolveToken(request)
//
//        if (token != null) {
//            if (!jwtUtil.validateToken(token)) {
//                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED.value())
//                return
//            }
//            val info = jwtUtil.getUserInfoFromToken(token)
//            setAuthentication(info.subject)
//        }
//        filterChain.doFilter(request, response)
//    }
//
//    private fun setAuthentication(email: String?) {
//        val authentication: Authentication = jwtUtil.createAuthentication(email!!)
//        SecurityContextHolder.getContext().authentication = authentication
//    }
//
//    private fun jwtExceptionHandler(response: HttpServletResponse, msg: String?, statusCode: Int) {
//        response.status = statusCode
//        response.contentType = "application/json"
//        try {
//            val json = ObjectMapper().writeValueAsString(ResponseEntity.status(statusCode).body(msg))
//            response.writer.write(json)
//        } catch (e: Exception) {
//            log.error(e.message)
//        }
//    }
//}
