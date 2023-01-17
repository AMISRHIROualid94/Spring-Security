package com.springframwork.springSecurity.controller;



import com.springframwork.springSecurity.entity.User;
import com.springframwork.springSecurity.registerForm.RegisterForm;
import com.springframwork.springSecurity.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class myController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public myController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    @RequestMapping("/registerForm")
    public String registerForm(){
        return "registration";
    }
    @PostMapping("/register")
    public String register(RegisterForm registerForm){
        userRepository.save(registerForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
