package com.teamsparta.assignment.domain.post.model

import com.teamsparta.assignment.domain.user.model.User
import com.teamsparta.assignment2.domain.post.dto.PostResponse
import com.teamsparta.assignment2.domain.user.dto.UserResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post(

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "createDt")
    val createDt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        title = title,
        username = user.username,
        content = content,
        createDt = createDt
    )
}