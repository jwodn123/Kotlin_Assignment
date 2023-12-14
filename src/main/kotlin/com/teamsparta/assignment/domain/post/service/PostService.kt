package com.teamsparta.assignment2.domain.post.service

import com.teamsparta.assignment2.domain.post.dto.CreatePostRequest
import com.teamsparta.assignment2.domain.post.dto.PostResponse
import com.teamsparta.assignment2.domain.post.dto.UpdatePostRequest

interface PostService {

    fun getAllPostList(): List<PostResponse>
    fun getPostById(postId: Long): PostResponse
    fun createPost(request: CreatePostRequest): PostResponse
    fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse
    fun deletePost(postId: Long)
}