//package com.teamsparta.assignment.jwt
//
//import com.teamsparta.assignment.model.User
//import com.teamsparta.assignment.repository.UserRepository
//import io.jsonwebtoken.*
//import io.jsonwebtoken.security.Keys
//import jakarta.annotation.PostConstruct
//import jakarta.servlet.http.HttpServletRequest
//import lombok.extern.slf4j.Slf4j
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Component
//import org.springframework.util.StringUtils
//import java.io.IOException
//import java.security.Key
//import java.util.*
//import javax.annotation.PostConstruct
//import javax.servlet.FilterChain
//import javax.servlet.ServletException
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
//@Slf4j
//@Component
//class JwtUtil(private val userRepository: UserRepository) : UserDetailsService {
//
//    @Value("\${jwt.secret.key}")
//    private val secretKey: String? = null
//    private var key: Key? = null
//    private val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
//
//    @PostConstruct
//    fun init() {
//        val bytes: ByteArray = Base64.getDecoder().decode(secretKey)
//        key = Keys.hmacShaKeyFor(bytes)
//    }
//
//    // header 토큰을 가져오기
//    fun resolveToken(request: HttpServletRequest): String? {
//        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(7)
//        }
//        return null
//    }
//
//    // 토큰 생성
//    fun createToken(user: User): String {
//        val date: Date = Date()
//
//        return BEARER_PREFIX +
//                Jwts.builder()
//                    .setSubject(user.getEmail())
//                    .setExpiration(Date(date.getTime() + TOKEN_TIME))
//                    .setIssuedAt(date)
//                    .signWith(key, signatureAlgorithm)
//                    .compact()
//    }
//
//    // 토큰 검증
//    fun validateToken(token: String?): Boolean {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
//            return true
//        } catch (e: SecurityException) {
//            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.")
//        } catch (e: MalformedJwtException) {
//            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.")
//        } catch (e: ExpiredJwtException) {
//            log.info("Expired JWT token, 만료된 JWT token 입니다.")
//        } catch (e: UnsupportedJwtException) {
//            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.")
//        } catch (e: IllegalArgumentException) {
//            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.")
//        }
//        return false
//    }
//
//    // 토큰에서 사용자 정보 가져오기
//    fun getUserInfoFromToken(token: String?): Claims {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
//    }
//
//    // 인증 객체 생성
//    @Transactional(readOnly = true)
//    fun createAuthentication(email: String): Authentication {
//        val userDetails = loadUserByUsername(email)
//        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
//    }
//
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(email: String): UserDetails {
//        val userOptional: Optional<User> = userRepository.findByEmail(email)
//        val user: User = userOptional.orElseThrow { CustomException(ErrorMessage.UNENROLLED_EMAIL) }
//        return user
//    }
//
//    companion object {
//        const val AUTHORIZATION_HEADER: String = "Authorization"
//        private const val BEARER_PREFIX = "Bearer "
//        private const val TOKEN_TIME = 60 * 60 * 1000L
//    }
//}