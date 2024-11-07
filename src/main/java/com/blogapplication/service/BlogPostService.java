package com.blogapplication.service;

import com.blogapplication.model.BlogPost;
import com.blogapplication.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    private final String imagePath = "src/main/resources/static/images/";

    public BlogPost createPost(String title, String content, String author, MultipartFile image) {
        BlogPost post = new BlogPost();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setDatePosted(LocalDate.now());

        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            post.setImageUrl(imageUrl);
        }

        return blogPostRepository.save(post);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    public BlogPost updatePost(Long id, String title, String content, String author, MultipartFile image) {
        BlogPost post = blogPostRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setAuthor(author);

            if (image != null && !image.isEmpty()) {
                String imageUrl = saveImage(image);
                post.setImageUrl(imageUrl);
            }

            return blogPostRepository.save(post);
        }
        return null;
    }

    public boolean deletePost(Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String saveImage(MultipartFile image) {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        try {
            image.transferTo(new File(imagePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/images/" + fileName;
    }
}
