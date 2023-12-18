package com.teamsparta.assignment.model

import com.teamsparta.assignment.common.MemberType
import com.teamsparta.assignment.dto.MemberUpdateRequest
import com.teamsparta.assignment.dto.SignUpRequest
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*

@Entity
class Member(
    @Column(nullable = false, scale = 20, unique = true)
    val username: String,
    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    val type: MemberType = MemberType.USER,

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    val posts: MutableList<Post> = mutableListOf()

) {
    val createdAt: LocalDateTime = LocalDateTime.now()
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
    companion object {
        fun from(request: SignUpRequest, encoder: PasswordEncoder) = Member(
            username = request.username,
            password = encoder.encode(request.password),
        )
    }

    fun update(newMember: MemberUpdateRequest, encoder: PasswordEncoder) {
        this.password = newMember.newPassword
            ?.takeIf { it.isNotBlank() }
            ?.let { encoder.encode(it) }
            ?: this.password
    }
}