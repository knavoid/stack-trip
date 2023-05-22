package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dto.Post;

import java.util.List;

public interface PostService {

    void createPost(Post post) throws Exception;

    List<Post> getPosts() throws Exception;

    Post getPost(int postId) throws Exception;

    void modifyPost(Post post) throws Exception;

    void increasePostViews(int postId) throws Exception;

    void removePost(int postId) throws Exception;
}
