package com.teamsparta.assignment

import com.teamsparta.assignment.common.MemberType
import com.teamsparta.assignment.model.Member
import com.teamsparta.assignment.repository.MemberRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AdminInitializer(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        memberRepository.save(Member("admin", encoder.encode("admin"), type = MemberType.ADMIN))
    }
}