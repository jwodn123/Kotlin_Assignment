package com.teamsparta.assignment.service

import com.teamsparta.assignment.dto.LoginRequest
import com.teamsparta.assignment.dto.SignUpRequest
import com.teamsparta.assignment.dto.UserResponse

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse
    fun login(request: LoginRequest): UserResponse
}