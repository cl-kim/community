package com.chaelin.community.controller;

import com.chaelin.community.domain.entity.Member;
import com.chaelin.community.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    MemberService memberService;
    // 어드민 페이지
    @GetMapping("/")
    public String dispAdmin(Model model) {
        List<Member> memberList = memberService.getList();
        model.addAttribute("memberList", memberList);
        return "/admin/main";
    }

    // 회원 탈퇴
    @PostMapping("/remove")
    public String remove(Long memberId) {

        memberService.deleteMember(memberId);

        return "/admin/main";
    }
}
