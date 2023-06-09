package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.dao.PostDAO;
import com.ssafy.enjoytrip.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public void createPost(Post post) throws Exception {
        postDAO.insert(post);
    }

    @Override
    public List<Post> getPosts() throws Exception {
        return postDAO.findAll();
    }

    @Override
    public List<Post> getPostsWithKeyword(String keyword) throws Exception {
        return postDAO.findAllByKeyword(keyword);
    }

    @Override
    public List<Post> getTop5Post() throws Exception {
        return postDAO.findByViewsDesc();
    }

    @Override
    public Post getPost(int postId) throws Exception {
        return postDAO.findByPostId(postId);
    }

    @Override
    public void modifyPost(Post post) throws Exception {
        postDAO.update(post);
    }

    @Override
    public void increasePostViews(int postId) throws Exception {
        postDAO.updateViews(postId);
    }

    @Override
    public void removePost(int postId) throws Exception {
        postDAO.deleteByPostId(postId);
    }

}
