package com.ssafy.enjoytrip.controller;

import java.net.URI;
import java.util.List;

import com.ssafy.enjoytrip.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.service.PostService;

import lombok.extern.slf4j.Slf4j;

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

	@GetMapping
	protected ResponseEntity<List<Post>> getPosts() throws Exception {
		return ResponseEntity.ok(postService.getPosts());
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
