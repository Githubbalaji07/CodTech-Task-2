package com.blogapplication.model;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BlogPost {
	
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    @Column(columnDefinition = "TEXT")
	    private String content;
	    private String author;
	    private LocalDate datePosted;
	    private String imageUrl;
	    
	    public BlogPost() {
			// TODO Auto-generated constructor stub
		}

		public BlogPost(Long id, String title, String content, String author, LocalDate datePosted, String imageUrl) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.author = author;
			this.datePosted = datePosted;
			this.imageUrl = imageUrl;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public LocalDate getDatePosted() {
			return datePosted;
		}

		public void setDatePosted(LocalDate datePosted) {
			this.datePosted = datePosted;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
	    
}
