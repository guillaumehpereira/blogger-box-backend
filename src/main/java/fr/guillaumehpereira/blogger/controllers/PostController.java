package fr.guillaumehpereira.blogger.controllers;

import fr.guillaumehpereira.blogger.dto.CreatePostRequest;
import fr.guillaumehpereira.blogger.models.Post;
import fr.guillaumehpereira.blogger.services.PostService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "API endpoints for managing blog posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Create a new post", description = "Creates a new post in the blog")
    public Post createPost(@RequestBody CreatePostRequest post) {
        return postService.createPost(post.getTitle(), post.getContent(), post.getCategoryId());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post", description = "Updates an existing post by ID")
    //réutilisation du même dto que pour la création car besoin des mêmes attributs
    public Post updatePost(@Parameter(description = "ID of the post to be updated") @PathVariable UUID id, @RequestBody CreatePostRequest updatePostRequest) {
        return postService.updatePost(id,updatePostRequest.getTitle(),updatePostRequest.getContent(),updatePostRequest.getCategoryId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing post", description = "Deletes an existing post by ID")
    public void deletePost(@Parameter(description = "ID of the post to be deleted") @PathVariable UUID id) {
        postService.deletePost(id);
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts ordered by creation date", description = "Retrieves all posts, optionally sorted by creation date")
    public List<Post> getAllPosts(@Parameter(description = "Optional sorting parameter") @RequestParam(required = false) String sortBy) {
        return postService.getAllPosts();
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Retrieve posts by category", description = "Retrieves all posts filtered by category")
    public List<Post> getPostsByCategory(@Parameter(description = "Category to filter posts") @PathVariable UUID categoryId) {
        return postService.getPostByCategory(categoryId);
    }
}
