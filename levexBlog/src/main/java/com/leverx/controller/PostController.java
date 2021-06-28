package com.leverx.controller;

import com.leverx.entity.Post;
import com.leverx.exception_handling.NoSuchPostException;
import com.leverx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPostOwnedByUser() {
        List<Post> postList = postService.getAllPostsOwnedByUser();
        return postList;
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            throw new NoSuchPostException("there is no post with ID = "
                    + id + " in database");
        }
        return post;
    }


    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody Post post,@PathVariable int id) {
        List<Post> postList = postService.getAllPostsOwnedByUser();
        postService.updatePostOwnedByUser(postList,post,id);
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void updatePost(@PathVariable int id) {
        List<Post> postList = postService.getAllPostsOwnedByUser();
        postService.deletePostOwnedByUser(postList,id);
    }
}
