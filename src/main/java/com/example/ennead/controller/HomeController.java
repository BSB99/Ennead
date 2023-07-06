package com.example.ennead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    public String categoryBoard(@RequestParam("category")String category,
                                Model model) {

        model.addAttribute("category", category);
        return "board";
    }
//    @GetMapping("/board")
//    public String categoryBoards(@RequestParam("category")String name, Model model){
//        model.addAttribute("category", name);
//        return "board";
//    }

    @GetMapping("/checkPwd")
    public String checkPwdView(){
        return "check-pwd";
    }

    @GetMapping("/profile")
    public String update_profile(){
        return "profile-update";
    }
}
