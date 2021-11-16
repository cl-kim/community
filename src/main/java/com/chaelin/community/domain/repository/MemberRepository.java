package com.chaelin.community.domain.repository;

import com.chaelin.community.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);
}
