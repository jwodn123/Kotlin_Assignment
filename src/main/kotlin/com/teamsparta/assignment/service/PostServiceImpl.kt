package com.teamsparta.assignment.service

import com.teamsparta.assignment.model.Post
import com.teamsparta.assignment.model.toResponse
import com.teamsparta.assignment.repository.PostRepository
import com.teamsparta.assignment.repository.MemberRepository
import com.teamsparta.assignment.dto.CreatePostRequest
import com.teamsparta.assignment.dto.PostResponse
import com.teamsparta.assignment.dto.UpdatePostRequest
import com.teamsparta.courseregistration.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository
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

        val member = memberRepository.findByUsername(request.username)
            ?: throw RuntimeException("사용자를 찾을 수 없습니다")

        return postRepository.save(
            Post(
                title = request.title,
                member = member,
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