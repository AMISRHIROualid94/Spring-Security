package com.springframwork.springSecurity.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class myController {


    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/welcome")
    public String Welcome(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user",user);
        return "welcomePage";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "registration";
    }
}
