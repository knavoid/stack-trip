package com.ssafy.enjoytrip.dao;

import java.util.List;

import com.ssafy.enjoytrip.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostDAO {

	void insert(Post post);

	List<Post> findAll();

	Post findByPostId(int postId);

	void update(Post post);

	void deleteByPostId(int postId);

}
