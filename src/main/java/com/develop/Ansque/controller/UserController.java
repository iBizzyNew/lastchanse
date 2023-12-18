package com.develop.Ansque.controller;

import com.develop.Ansque.service.impl.PostServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.develop.Ansque.entity.UserEntity;
import com.develop.Ansque.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final PostServiceImpl postService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registerForm";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") UserEntity user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "home";
    }

}
