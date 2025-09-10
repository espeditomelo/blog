package com.espeditomelo.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @NotBlank
    @Column(nullable = false)
    private String status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PostCategory> postCategories = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "postLike", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @Column(name = "main_image_url")
    private String mainImageUrl;

    public Post() {}

    // Getters and Setters
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public void addCategory(Category category){
        PostCategory postCategory = new PostCategory(this, category);
        postCategories.add(postCategory);
    }

    public void removeCategory(Category category){
        PostCategory postCategory = postCategories.stream()
                .filter(pc -> pc.getCategory().equals(category))
                .findFirst()
                .orElse(null);
        if(postCategory != null){
            postCategories.remove(postCategory);
        }
    }

    @Transient
    public List<Category> getCategories(){
        return postCategories.stream()
                .map(PostCategory::getCategory)
                .collect(Collectors.toList());
    }


    public List<PostCategory> getPostCategories() {
        return postCategories;
    }

    public void setPostCategories(List<PostCategory> postCategories) {
        this.postCategories = postCategories;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}