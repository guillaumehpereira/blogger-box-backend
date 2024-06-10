package fr.guillaumehpereira.blogger.services;

import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
import fr.guillaumehpereira.blogger.exceptions.PostNotFoundByIdException;
import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(UUID id) throws PostNotFoundByIdException;
    Post createPost(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException;
    Post updatePost(UUID id, String title, String content, UUID categoryId) throws PostNotFoundByIdException, CategoryNotFoundByIdException;
    List<Post> getPostByCategory(UUID categoryId) throws CategoryNotFoundByIdException;
    void deletePost(UUID id) throws PostNotFoundByIdException;
}
