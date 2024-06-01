package fr.guillaumehpereira.blogger.services.impl;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.models.Post;
import fr.guillaumehpereira.blogger.repositories.PostRepository;
import fr.guillaumehpereira.blogger.services.CategoryService;
import fr.guillaumehpereira.blogger.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final CategoryService categoryService;


    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(UUID id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post createPost(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        Post newPost = new Post(title, content, category);
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(UUID id, String title, String content, UUID categoryId) {
        Post post = getPostById(id);
        Category category = categoryService.getById(categoryId);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setCategory(category);
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public List<Post> getPostByCategory(UUID categoryId) {
        return postRepository.findByCategory_Id(categoryId);
    }

    @Override
    public boolean deletePost(UUID id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
