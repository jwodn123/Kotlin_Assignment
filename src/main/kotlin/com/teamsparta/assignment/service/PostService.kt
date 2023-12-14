package com.teamsparta.assignment.service

import com.teamsparta.assignment.dto.CreatePostRequest
import com.teamsparta.assignment.dto.PostResponse
import com.teamsparta.assignment.dto.UpdatePostRequest

interface PostService {

    fun getAllPostList(): List<PostResponse>
    fun getPostById(postId: Long): PostResponse
    fun createPost(request: CreatePostRequest): PostResponse
    fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse
    fun deletePost(postId: Long)
}