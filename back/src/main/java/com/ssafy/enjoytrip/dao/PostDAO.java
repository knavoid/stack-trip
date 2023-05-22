package com.ssafy.enjoytrip.dao;

import com.ssafy.enjoytrip.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostDAO {

    void insert(Post post);

    List<Post> findAll();

    List<Post> findAllByKeyword(String keyword);

    Post findByPostId(int postId);

    void update(Post post);

    void updateViews(int postId);

    void deleteByPostId(int postId);

}
