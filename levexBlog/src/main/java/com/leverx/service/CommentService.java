package com.leverx.service;

import com.leverx.entity.Comment;
import com.leverx.entity.Post;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentsOfPost(int id);

    public Comment addNewCommentToPost(Comment comment, Post post);

    public void deleteComment(int id);
}
