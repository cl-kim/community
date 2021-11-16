package com.chaelin.community.service;

import com.chaelin.community.dto.MemberDTO;
import com.chaelin.community.domain.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    Long joinUser(MemberDTO memberDTO);
    List<Member> getList();
    MemberDTO getMember(String email);
    boolean isDuplicate(String id);
    void modifyMember(MemberDTO memberDTO);
    void deleteMember(Long memberId);

    default Member dtoToEntity(MemberDTO dto) {
        Member entity = Member.builder()
                .memberId(dto.getMemberId())
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
        return entity;
    }

    default MemberDTO entityToDto(Member entity){

        MemberDTO dto  = MemberDTO.builder()
                .memberId(entity.getMemberId())
                .email(entity.getEmail())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
