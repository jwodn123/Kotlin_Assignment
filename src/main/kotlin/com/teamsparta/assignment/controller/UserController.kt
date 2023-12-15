package com.teamsparta.assignment.controller

import com.teamsparta.assignment.dto.LoginRequest
import com.teamsparta.assignment.dto.SignUpRequest
import com.teamsparta.assignment.dto.UserResponse
import com.teamsparta.assignment.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    // 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }

    // 로그인
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<UserResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))
    }

}