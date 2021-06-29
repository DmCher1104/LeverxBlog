package com.leverx.dao.impl;

import com.leverx.dao.CommentDao;
import com.leverx.entity.Comment;
import com.leverx.entity.Post;
import com.leverx.entity.User;
import com.leverx.exception_handling.NoSuchException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Comment> getCommentsOfPost(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Comment> query = session.createQuery("from Comment where post.id=: id");
        query.setParameter("id", id);


        return query.getResultList();
    }

    @Override
    public Comment addNewCommentToPost(Comment comment, Post post) {
        Session session = sessionFactory.getCurrentSession();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Query<User> query = session.createQuery("from User where username =: username");
        query.setParameter("username", username);
        List<User> userFromDB = query.getResultList();
        comment.setPost(post);
        comment.setUser(userFromDB.get(0));
        comment.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        session.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(int id) {
       Session session = sessionFactory.getCurrentSession();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Query<User> query = session.createQuery("from User where username =: username");
        query.setParameter("username", username);
        List<User> userFromDB = query.getResultList();
        Comment comment = session.get(Comment.class,id);
        if(!(comment.getUser().getId() == userFromDB.get(0).getId())) {
            throw new NoSuchException("you don't have permission to delete comment = "
                    + id+ ". It's not yours");
        }
        Query<Comment> query1 = session.createQuery("delete from Comment "
                + "where id =:id");
        query1.setParameter("id", id);
        query1.executeUpdate();

    }
}
