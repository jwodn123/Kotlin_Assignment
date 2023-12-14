package com.teamsparta.assignment2.domain.user.service

import com.teamsparta.assignment2.domain.user.dto.LoginRequest
import com.teamsparta.assignment2.domain.user.dto.SignUpRequest
import com.teamsparta.assignment2.domain.user.dto.UserResponse

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse
    fun login(request: LoginRequest): UserResponse
}