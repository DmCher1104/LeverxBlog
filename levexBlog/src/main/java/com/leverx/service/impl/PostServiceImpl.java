package com.leverx.service.impl;

import com.leverx.dao.PostDao;
import com.leverx.entity.Post;
import com.leverx.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    @Transactional
    public Post getPostById(int id) {
        return postDao.getPostById(id);
    }

    @Override
    @Transactional
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    @Override
    @Transactional
    public void addNewPost(Post post) {
        postDao.addNewPost(post);
    }

    @Override
    @Transactional
    public void updatePostOwnedByUser(List<Post> postList,Post post, int id) {
        postDao.updatePost(postList,post, id);
    }

    @Override
    @Transactional
    public List<Post> getAllPostsOwnedByUser() {
        return postDao.getAllPostsOwnedByUser();
    }

    @Override
    @Transactional
    public void deletePostOwnedByUser(List<Post> postList,int id) {
        postDao.deletePostOwnedByUser(postList,id);
    }
}


