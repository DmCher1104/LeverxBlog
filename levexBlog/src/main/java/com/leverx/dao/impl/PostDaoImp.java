package com.leverx.dao.impl;

import com.leverx.dao.PostDao;
import com.leverx.entity.Post;
import com.leverx.entity.User;
import com.leverx.exception_handling.NoSuchPostException;
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
public class PostDaoImp implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Post getPostById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Post.class, id);
    }

    @Override
    public List<Post> getAllPosts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Post> query = session.createQuery("from Post", Post.class);
        List<Post> postList = query.getResultList();
        return postList;
    }

    @Override
    public void addNewPost(Post post) {
        Session session = sessionFactory.getCurrentSession();
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Query<User> query = session.createQuery("from User where username =: username");
        query.setParameter("username", username);
        List<User> userFromDB = query.getResultList();

        post.setUser(userFromDB.get(0));
        post.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date()));
        session.save(post);
    }

    @Override
    public List<Post> getAllPostsOwnedByUser() {
        Session session = sessionFactory.getCurrentSession();

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Query<User> query1 = session.createQuery("from User where username =: username");
        query1.setParameter("username", username);
        List<User> usersFromDB = query1.getResultList();
        User user = usersFromDB.get(0);

        Query<Post> query2 = session.createQuery("from Post where user =: user");

        query2.setParameter("user", user);
        List<Post> postList = query2.getResultList();
        return postList;
    }

    @Override
    public void updatePost(List<Post> postList, Post post, int id) {
        Session session = sessionFactory.getCurrentSession();
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId() == id) {
                postList.get(0).setTitle(post.getTitle());
                postList.get(0).setText(post.getText());
                postList.get(0).setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                session.update(postList.get(0));
                return;
            }
        }
        throw new NoSuchPostException("there is no your post with ID = "
                + id + " in database");
    }

    @Override
    public void deletePostOwnedByUser(List<Post> postList, int id) {
        Session session = sessionFactory.getCurrentSession();
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId() == id) {
                Query<Post> query = session.createQuery("delete from Post "
                        + "where id =:id");
                query.setParameter("id", id);
                query.executeUpdate();
                return;
            }
        }

        throw new NoSuchPostException("there is no your post with ID = "
                + id + " in database");
    }
}
