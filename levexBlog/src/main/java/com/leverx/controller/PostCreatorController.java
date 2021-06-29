package com.leverx.controller;

import com.leverx.entity.Post;
import com.leverx.entity.Tag;
import com.leverx.service.PostService;
import com.leverx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostCreatorController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @GetMapping("/createNewPost")
    public String createNewPost(Model model){
        Post post = new Post();
       /* List<Tag> tagsList = tagService.getAllTags();
//        model.addAttribute("tagList", tagsList);
        model.addAttribute("tag1" ,tagsList.get(0) );
        model.addAttribute("tag2" ,tagsList.get(1) );
        model.addAttribute("tag3" ,tagsList.get(2) );
        model.addAttribute("posts", post);*/
        model.addAttribute("posts",post);
        return "createNewPost";
    }

    @PostMapping("/createNewPost")
    public String createNewPost(@ModelAttribute("posts") Post post) {

        postService.addNewPost(post);
        return "redirect:/";
    }

}
