package com.chaelin.community.controller;

import com.chaelin.community.domain.entity.User;
import com.chaelin.community.service.UserService;
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
    UserService userService;
    // 어드민 페이지
    @GetMapping("/")
    public String dispAdmin(Model model) {
        List<User> userList = userService.getList();
        model.addAttribute("userList", userList);
        return "/admin/main";
    }

    // 회원 탈퇴
    @PostMapping("/remove")
    public String remove(Long userId) {

        userService.deleteUser(userId);

        return "/admin/main";
    }
}
