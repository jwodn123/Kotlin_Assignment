package com.teamsparta.assignment.service

import com.teamsparta.assignment.dto.SignInRequest
import com.teamsparta.assignment.dto.SignInResponse
import com.teamsparta.assignment.dto.SignUpRequest
import com.teamsparta.assignment.dto.SignUpResponse
import com.teamsparta.assignment.model.Member
import com.teamsparta.assignment.repository.MemberRepository
import com.teamsparta.assignment.security.TokenProvider
import com.teamsparta.assignment.util.flushOrThrow
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder
) {
    @Transactional
    fun registMember(request: SignUpRequest) = SignUpResponse.from(
        memberRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) { save(Member.from(request, encoder)) }
    )

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val member = memberRepository.findByUsername(request.username)
            ?.takeIf { encoder.matches(request.password, it.password) } ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        val token = tokenProvider.createToken("${member.id}:${member.type}")
        return SignInResponse(member.type, token)
    }
}