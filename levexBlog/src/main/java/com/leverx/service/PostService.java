package com.leverx.service;

import com.leverx.entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();
    public List<Post> getAllPostsOwnedByUser();
    public Post getPostById(int id);
    public void addNewPost(Post post);
    public void updatePostOwnedByUser(List<Post> postList,Post post,int id);
    public void deletePostOwnedByUser(List<Post> postList,int id);
}
