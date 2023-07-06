package com.example.ennead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/boardInfo")
    public String board(Model model, @RequestParam("targetId") int targetId) {
        model.addAttribute("targetId", targetId);

        return "boardInfo";
    }
    @GetMapping("/board")
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
