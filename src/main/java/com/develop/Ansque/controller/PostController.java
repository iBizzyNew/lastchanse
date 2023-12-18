package com.develop.Ansque.controller;

import com.develop.Ansque.entity.PostEntity;
import com.develop.Ansque.entity.UserEntity;
import com.develop.Ansque.service.PostService;
import com.develop.Ansque.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserServiceImpl userService;

    @GetMapping
    public String listPosts(Model model) {
        Collection<PostEntity> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new PostEntity());
        return "posts/createForm";
    }

    @PostMapping("/createNewPost")
    public String createPost(@ModelAttribute PostEntity post, RedirectAttributes redirectAttributes, Principal principal) {
        UserEntity user = userService.getUserByPrincipal(principal);
        post.setCreationDate(LocalDate.now());
        post.setUser(user);
        post.setStatus(true);
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post created successfully!");
        return "redirect:/posts";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<PostEntity> post = postService.getById(id);
        post.ifPresent(p -> model.addAttribute("post", p));
        return post.map(p -> "posts/editForm").orElse("redirect:/posts");
    }


    @GetMapping("/post/{id}")
    public String getPost(Model model, @PathVariable Long id){
        PostEntity post = postService.getById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        System.err.println(post.getTitle());
        System.err.println(post.getText());
        model.addAttribute("post", post);
        return "post";
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
        return "redirect:/posts";
    }
}
