package com.teamsparta.assignment2.domain.post.dto

data class UpdatePostRequest(
    val title: String,
    val username: String,
    val content: String
)