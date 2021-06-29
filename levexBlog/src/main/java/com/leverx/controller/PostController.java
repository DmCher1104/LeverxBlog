package com.leverx.controller;

import com.leverx.entity.Comment;
import com.leverx.entity.Post;
import com.leverx.exception_handling.NoSuchException;
import com.leverx.service.CommentService;
import com.leverx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/my")
    public List<Post> getAllPostOwnedByUser() {

        List<Post> postList = postService.getAllPostsOwnedByUser();
        return postList;
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            throw new NoSuchException("there is no post with ID = "
                    + id + " in database");
        }
        return post;
    }


    @PutMapping("/posts/{id}")
    public Post updatePost(@RequestBody Post post, @PathVariable int id) {
        List<Post> postList = postService.getAllPostsOwnedByUser();
        postService.updatePostOwnedByUser(postList, post, id);
        return post;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        List<Post> postList = postService.getAllPostsOwnedByUser();
        postService.deletePostOwnedByUser(postList, id);
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getCommentsOfPost(@PathVariable int id) {
        List<Comment> listComment = commentService.getCommentsOfPost(id);
        return listComment;
    }

    @PostMapping("/posts/{id}/comments")
    public Comment addCommentsOfPost(@RequestBody Comment comment, @PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            throw new NoSuchException("there is no post with ID = "
                    + id + " in database");
        }
        comment = commentService.addNewCommentToPost(comment, post);
        return comment;
    }

    @DeleteMapping("/posts/{idPost}/comments/{idComment}")
    public void deleteComment(@PathVariable int idPost, @PathVariable int idComment) {
        Post post = postService.getPostById(idPost);
        if (post == null) {
            throw new NoSuchException("there is no post with ID = "
                    + idPost + " in database");
        }
        List<Comment> listComment = commentService.getCommentsOfPost(idPost);
        for (int i = 0; i < listComment.size(); i++) {
            if (listComment.get(i).getId() == idComment) {
                commentService.deleteComment(idComment);
                return;
            }
        }
        throw new NoSuchException("there is no comment with ID = "
                + idComment + " in database");

    }
}
