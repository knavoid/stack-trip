package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.dto.Post;
import com.ssafy.enjoytrip.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    protected ResponseEntity<Post> createPost(@RequestBody Post newPost) throws Exception {
        Post post = postService.getPost(newPost.getPostId());
        if (post == null) {
            postService.createPost(newPost);
            return ResponseEntity.created(URI.create("/post/" + newPost.getPostId())).body(newPost);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/list")
    protected ResponseEntity<List<Post>> getPosts() throws Exception {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/list/search")
    protected ResponseEntity<List<Post>> getPostsWithKeyword(@RequestParam String keyword) throws Exception {
        return ResponseEntity.ok(postService.getPostsWithKeyword(keyword));
    }

    @GetMapping("/{postId}")
    protected ResponseEntity<Post> getPost(@PathVariable int postId) throws Exception {
        Post post = postService.getPost(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{postId}")
    protected ResponseEntity<Post> modifyPost(@RequestBody Post post) throws Exception {
        postService.modifyPost(post);
        return ResponseEntity.ok(post);
    }

    @PatchMapping("/{postId}/views")
    protected ResponseEntity<?> increasePostViews(@PathVariable int postId) throws Exception {
        Post post = postService.getPost(postId);
        if (post != null) {
            postService.increasePostViews(postId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{postId}")
    protected ResponseEntity<?> removePost(@PathVariable int postId) throws Exception {
        Post post = postService.getPost(postId);
        if (post != null) {
            postService.removePost(postId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler
    public String handleException(Exception exception, Model model) {
        log.error(exception.getMessage(), exception);
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

}
