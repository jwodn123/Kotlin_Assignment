package com.teamsparta.assignment.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(

    @Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "형식에 맞지 않습니다.")
    val username: String,

    @Size(min = 4, max = 10, message = "비밀번호는 4 이상, 10 이하만 가능합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-zA-Z0-9`~!@#$%^&*()_=+|{};:,.<>/?]*$", message = "비밀번호는 영문과 숫자로 구성되어야하며 특수문자를 포함할 수 있습니다.")
    val password: String
)
