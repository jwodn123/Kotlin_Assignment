package com.teamsparta.assignment2.domain.post.service

import com.teamsparta.assignment.domain.post.model.Post
import com.teamsparta.assignment.domain.post.model.toResponse
import com.teamsparta.assignment.domain.post.repository.PostRepository
import com.teamsparta.assignment.domain.user.model.User
import com.teamsparta.assignment.domain.user.repository.UserRepository
import com.teamsparta.assignment2.domain.post.dto.CreatePostRequest
import com.teamsparta.assignment2.domain.post.dto.PostResponse
import com.teamsparta.assignment2.domain.post.dto.UpdatePostRequest
import com.teamsparta.courseregistration.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {

    override fun getAllPostList(): List<PostResponse> {
        return postRepository.findAll().map { it.toResponse() }
    }

    override fun getPostById(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        return post.toResponse()
    }

    @Transactional
    override fun createPost(request: CreatePostRequest): PostResponse {

        val user = userRepository.findByUsername(request.username)
            ?: throw RuntimeException("사용자를 찾을 수 없습니다")

        return postRepository.save(
            Post(
                title = request.title,
                user = user,
                content = request.content,
            )
        ).toResponse()
    }

    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val (title, content) = request

        post.title = title
        post.content = content

        return postRepository.save(post).toResponse()
    }

    @Transactional
    override fun deletePost(postId: Long) {
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        postRepository.delete(post)
    }
}