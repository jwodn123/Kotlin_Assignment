package com.teamsparta.assignment2.domain.post.controller

import com.teamsparta.assignment2.domain.post.dto.CreatePostRequest
import com.teamsparta.assignment2.domain.post.dto.PostResponse
import com.teamsparta.assignment2.domain.post.dto.UpdatePostRequest
import com.teamsparta.assignment2.domain.post.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/posts")
@RestController
class PostController(private val postService: PostService) {

    // 게시글 전체 조회
    @GetMapping
    fun getPostList(): ResponseEntity<List<PostResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getAllPostList())
    }
    
    // 게시글 단일 조회
    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.getPostById(postId))
    }

    // 게시글 생성
    @PostMapping
    fun createPost(@RequestBody createPostRequest: CreatePostRequest): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postService.createPost(createPostRequest))
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody updatePostRequest: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(postService.updatePost(postId, updatePostRequest))
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Unit> {
        postService.deletePost(postId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
    
    

}