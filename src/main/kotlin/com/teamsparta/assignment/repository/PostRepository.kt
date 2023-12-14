package com.teamsparta.assignment.repository

import com.teamsparta.assignment.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {

}