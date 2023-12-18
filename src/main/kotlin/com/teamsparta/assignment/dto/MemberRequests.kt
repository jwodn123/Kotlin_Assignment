package com.teamsparta.assignment.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignUpRequest(
    @Schema(description = "회원 아이디", example = "colabear754")
    val username: String,
    @Schema(description = "회원 비밀번호", example = "1234")
    var password: String,
)

data class SignInRequest(
    @Schema(description = "회원 아이디", example = "colabear754")
    val username: String,
    @Schema(description = "회원 비밀번호", example = "1234")
    val password: String
)

data class MemberUpdateRequest(
    @Schema(description = "회원 비밀번호", example = "1234")
    var password: String,
    @Schema(description = "회원 새 비밀번호", example = "1234")
    var newPassword: String? = null,
)