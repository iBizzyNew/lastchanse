package com.develop.Ansque.controller;

import com.develop.Ansque.entity.PostEntity;
import com.develop.Ansque.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String listPosts(Model model) {
        Collection<PostEntity> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "posts/list"; // The name of the Thymeleaf template to render the list of posts
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new PostEntity());
        return "posts/createForm"; // The name of the Thymeleaf template for creating a new post
    }

    @PostMapping("/createNewPost")
    public String createPost(@ModelAttribute PostEntity post, RedirectAttributes redirectAttributes) {
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post created successfully!");
        return "redirect:/posts"; // Redirect to the list of posts
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<PostEntity> post = postService.getById(id);
        post.ifPresent(p -> model.addAttribute("post", p));
        return post.map(p -> "posts/editForm").orElse("redirect:/posts");
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute PostEntity post, RedirectAttributes redirectAttributes) {
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post updated successfully!");
        return "redirect:/posts"; // Redirect to the list of posts
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        postService.getById(id).ifPresent(post -> postService.delete(post));
        redirectAttributes.addFlashAttribute("success", "Post deleted successfully!");
        return "redirect:/posts"; // Redirect to the list of posts
    }
}
