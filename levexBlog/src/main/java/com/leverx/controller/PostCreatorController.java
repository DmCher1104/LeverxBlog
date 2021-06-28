package com.leverx.controller;

import com.leverx.entity.Post;
import com.leverx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostCreatorController {

    @Autowired
    private PostService postService;

    @GetMapping("/createNewPost")
    public String createNewPost(Model model){
        Post post = new Post();
        model.addAttribute("posts", post);
        return "createNewPost";
    }

    @PostMapping("/createNewPost")
    public String createNewPost(@ModelAttribute("posts") Post post) {
        postService.addNewPost(post);
        return "redirect:/";
    }

}
