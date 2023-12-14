package com.teamsparta.assignment2.domain.user.service

import com.teamsparta.assignment.domain.user.model.User
import com.teamsparta.assignment.domain.user.model.toResponse
import com.teamsparta.assignment.domain.user.repository.UserRepository
import com.teamsparta.assignment2.domain.user.dto.LoginRequest
import com.teamsparta.assignment2.domain.user.dto.SignUpRequest
import com.teamsparta.assignment2.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("이미 존재하는 이름입니다")
        }

        return userRepository.save(
            User(
                username = request.username,
                password = request.password
            )
        ).toResponse()
    }

    @Transactional
    override fun login(request: LoginRequest): UserResponse {

        val user = userRepository.findByUsername(request.username)
            ?: throw RuntimeException("사용자를 찾을 수 없습니다")

        if (user.password != request.password) {
            throw RuntimeException("유효하지 않은 비밀번호입니다")
        }

        return user.toResponse()
    }
}