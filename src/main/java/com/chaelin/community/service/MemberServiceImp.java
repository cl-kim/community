package com.chaelin.community.service;

import com.chaelin.community.domain.Role;
import com.chaelin.community.domain.entity.Board;
import com.chaelin.community.dto.BoardDTO;
import com.chaelin.community.dto.MemberDTO;
import com.chaelin.community.domain.entity.Member;
import com.chaelin.community.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {
    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

        return memberRepository.save(dtoToEntity(memberDTO)).getMemberId();
    }

    @Override
    public List<Member> getList() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;

    }

    @Override
    public MemberDTO getMember(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            Member entity = member.get();
            return entityToDto(entity);
        }
        return null;
    }

    @Override
    public boolean isDuplicate(String id) {
        if (memberRepository.findByEmail(id) == null)
            return false;
        return true;
    }

    @Override
    public void modifyMember(MemberDTO memberDTO) {
        //업데이트 하는 항목은 이름이나 비밀번호
        Optional<Member> result = memberRepository.findById(memberDTO.getMemberId());

        if (result.isPresent()) {

            Member entity = result.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));

            entity.changeUserName(memberDTO.getUserName());
            entity.changePassword(memberDTO.getPassword());

            memberRepository.save(entity);

        }
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByEmail(userEmail);
        Member member = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getEmail(), member.getPassword(), authorities);
    }
}
