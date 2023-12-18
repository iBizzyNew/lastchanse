package com.develop.Ansque.controller;



import com.develop.Ansque.entity.MessageEntity;
import com.develop.Ansque.entity.PostEntity;
import com.develop.Ansque.entity.UserEntity;
import com.develop.Ansque.service.PostService;
import com.develop.Ansque.service.impl.MessageServiceImpl;
import com.develop.Ansque.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;
    private final UserServiceImpl userService;
    private final MessageServiceImpl messageService;



    @GetMapping("/comment/{postId}")
    public String showComment(@PathVariable Long postId, Model model, Principal principal) {

//        UserEntity user = userService.getUserByPrincipal(principal);
        PostEntity post = postService.getById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));

        if(post != null){
            MessageEntity message = new MessageEntity();
            model.addAttribute("postId", postId);
            model.addAttribute("message", message);
            return "commentForm";
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/comment/{postId}")
    public String validateComment(@ModelAttribute MessageEntity message, @PathVariable Long postId, Principal principal) {
        message.setPost(postService.getById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found")));
        message.setUser(userService.getUserByPrincipal(principal));
        message.setDate(LocalDateTime.now());
        messageService.save(message);
        return "redirect:/home";
    }

}