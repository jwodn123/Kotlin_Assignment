package com.teamsparta.assignment.dto

import java.time.LocalDateTime

data class PostResponse(
    val id: Long,
    var title: String,
    val username: String,
    var content: String,
    val createDt: LocalDateTime
)
