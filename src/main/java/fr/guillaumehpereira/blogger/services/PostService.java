package fr.guillaumehpereira.blogger.services;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(UUID id);
    Post createPost(String title, String content, Category category);
    Post updatePost(UUID id, String title, String content);
    boolean deletePost(UUID id);
}
