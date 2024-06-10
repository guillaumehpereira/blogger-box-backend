package fr.guillaumehpereira.blogger.services.impl;

import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
import fr.guillaumehpereira.blogger.exceptions.PostNotFoundByIdException;
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
    public Post getPostById(UUID id) throws PostNotFoundByIdException {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post createPost(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(categoryId);
        Post newPost = new Post(title, content, category);
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(UUID id, String title, String content, UUID categoryId) throws PostNotFoundByIdException, CategoryNotFoundByIdException {
        Post post = getPostById(id);
        Category category = categoryService.getById(categoryId);
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostByCategory(UUID categoryId) throws CategoryNotFoundByIdException {
        //Renvoie l'exception
        categoryService.getById(categoryId);
        return postRepository.findByCategory_Id(categoryId);
    }

    @Override
    public void deletePost(UUID id) throws PostNotFoundByIdException {
        getPostById(id);
        postRepository.deleteById(id);
    }
}
