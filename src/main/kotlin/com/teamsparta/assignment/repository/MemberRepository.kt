package com.teamsparta.assignment.repository

import com.teamsparta.assignment.common.MemberType
import com.teamsparta.assignment.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository: JpaRepository<Member, UUID> {
    fun findByUsername(username: String): Member?
    fun findAllByType(type: MemberType): List<Member>
}