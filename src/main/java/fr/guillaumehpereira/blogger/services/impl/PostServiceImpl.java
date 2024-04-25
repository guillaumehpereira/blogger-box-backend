package fr.guillaumehpereira.blogger.services.impl;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.models.Post;
import fr.guillaumehpereira.blogger.repositories.PostRepository;
import fr.guillaumehpereira.blogger.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
    public Post createPost(String title, String content, Category category) {
        Post newPost = new Post(title, content, category);
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(UUID id, String title, String content, Category category) {
        Post post = getPostById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setCategory(category);
            return postRepository.save(post);
        }
        return null;
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
