package com.leverx.dao;

import com.leverx.entity.Post;
import com.leverx.entity.User;

import java.util.List;

public interface PostDao {
    public List<Post> getAllPosts();
    public Post getPostById(int id);
    public void addNewPost(Post post);
    public void updatePost(List<Post> postList,Post post,int id);
    public List<Post> getAllPostsOwnedByUser();
    public void deletePostOwnedByUser(List<Post> postList,int id);
}
