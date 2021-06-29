package com.leverx.service.impl;

import com.leverx.dao.CommentDao;
import com.leverx.entity.Comment;
import com.leverx.entity.Post;
import com.leverx.service.CommentService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional
    public List<Comment> getCommentsOfPost(int id) {
        return commentDao.getCommentsOfPost(id);
    }

    @Override
    @Transactional
    public Comment addNewCommentToPost(Comment comment, Post post) {
        return commentDao.addNewCommentToPost(comment, post);
    }

    @Override
    @Transactional
    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }
}
