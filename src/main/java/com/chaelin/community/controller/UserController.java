package com.chaelin.community.controller;

import com.chaelin.community.dto.UserDTO;
import com.chaelin.community.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;


    @GetMapping("/signup")
    public String dispSignup(){
        return "/user/signup";
    }

    @PostMapping("/signup")
    public String execSignup(UserDTO userDTO){
        userService.joinUser(userDTO);

        return "redirect:/user/login";
    }
    // 로그인 페이지
    @GetMapping("/login")
    public String dispLogin() {

        return "/user/login";
    }


    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public String dispLoginResult() {
        return "/index";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "/index";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String dispDenied() {
        return "/user/denied";
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String dispInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        model.addAttribute("userDTO", userService.getUser(userDetails.getUsername()));
        return "/user/info";
    }

    // 내 정보 수정
    @PostMapping("/info/modify")
    public String modify(UserDTO userDTO){

        userService.modifyUser(userDTO);

        return "redirect:/user/info";

    }

    // 회원 탈퇴
    @PostMapping("/info/remove")
    public String remove(Long userId){

        userService.deleteUser(userId);

        return "/index";

    }


}
