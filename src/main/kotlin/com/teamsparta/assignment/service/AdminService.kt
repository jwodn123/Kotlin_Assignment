package com.teamsparta.assignment.service


import com.teamsparta.assignment.common.MemberType
import com.teamsparta.assignment.dto.MemberInfoResponse
import com.teamsparta.assignment.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMembers(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.USER).map(MemberInfoResponse::from)

    @Transactional(readOnly = true)
    fun getAdmins(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.ADMIN).map(MemberInfoResponse::from)
}