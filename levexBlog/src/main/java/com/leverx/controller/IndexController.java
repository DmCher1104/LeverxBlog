package com.leverx.controller;

import com.leverx.entity.Post;
import com.leverx.entity.Status;
import com.leverx.exception_handling.NoSuchPostException;
import com.leverx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String getAllPost(Model model) {
        List<Post> postList = postService.getAllPosts();
        List<Post> publicPostList = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            if(postList.get(i).getStatus() == Status.PUBLIC){
                publicPostList.add(postList.get(i));
            }
        }
        model.addAttribute("postList", publicPostList);
        return "index";
    }

}
