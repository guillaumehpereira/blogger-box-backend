package fr.guillaumehpereira.blogger.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private Category category;

    public Post(String title, String content, Category category) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.creationDate = LocalDateTime.now();
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
