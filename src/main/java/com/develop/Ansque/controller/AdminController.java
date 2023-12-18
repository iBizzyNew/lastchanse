package com.develop.Ansque.controller;

import com.develop.Ansque.service.impl.MessageServiceImpl;
import com.develop.Ansque.service.impl.PostServiceImpl;
import com.develop.Ansque.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.ui.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserServiceImpl userService;

    private final PostServiceImpl postService;
    private final MessageServiceImpl messageService;


    @GetMapping("/admin")
    public String getAdminPanel(Model model, Principal principal){
        return "admin";
    }

    @GetMapping("/admin/posts")
    public String getPosts(Model model){
        model.addAttribute("posts", postService.getAll());
        return "admin/posts";
    }

    @GetMapping("/admin/messages")
    public String getMessages(Model model){
        model.addAttribute("messages", messageService.getAll());
        return "admin/messages";
    }

    @PostMapping("/admin/delete/post/{postId}")
    public String deletePost(@PathVariable Long postId){
        postService.delete(postService.getById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found")));
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete/message/{messageId}")
    public String deleteMessage(@PathVariable Long messageId){
        messageService.delete(messageService.getById(messageId));
        return "redirect:/admin";
    }
}
