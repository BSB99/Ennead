package com.example.ennead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/board")
    public String board() {
        return "boardInfo";
    }
    @GetMapping("/categoryboard")
    public String categoryboard(){
        return "board";
    }

    @GetMapping("/checkPwd")
    public String checkPwdView(){
        return "check-pwd";
    }

    @GetMapping("/profile/{user_no}")
    public String update_profile(){
        return "profile-update";
    }
}
