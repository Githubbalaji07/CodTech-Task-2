package com.blogapplication.controller;

import com.blogapplication.model.BlogPost;
import com.blogapplication.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/api/posts")
    public ResponseEntity<BlogPost> createPost(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author,
            @RequestParam(required = false) MultipartFile image) {

        BlogPost post = blogPostService.createPost(title, content, author, image);
        return ResponseEntity.status(201).body(post);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.getAllPosts());
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        BlogPost post = blogPostService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<BlogPost> updatePost(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author,
            @RequestParam(required = false) MultipartFile image) {

        BlogPost updatedPost = blogPostService.updatePost(id, title, content, author, image);
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = blogPostService.deletePost(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
