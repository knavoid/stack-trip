package com.ssafy.enjoytrip.service;

import java.util.List;

import com.ssafy.enjoytrip.dto.Post;

public interface PostService {
	
	void createPost(Post post) throws Exception;
	
	List<Post> getPosts() throws Exception;
	
	Post getPost(int postId) throws Exception;
	
	void modifyPost(Post post) throws Exception;

	void removePost(int postId) throws Exception;
}
