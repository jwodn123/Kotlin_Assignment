package com.teamsparta.assignment.model

import com.teamsparta.assignment.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(

    @Column(name = "username", nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val postLists: MutableList<Post> = mutableListOf()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        username = username
    )
}