package fr.guillaumehpereira.blogger.services;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> posts = new ArrayList<>();

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post getPostById(UUID id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post createPost(String title, String content, Category category) {
        Post newPost = new Post(title, content, category);
        posts.add(newPost);
        return newPost;
    }

    @Override
    public Post updatePost(UUID id, String title, String content) {
        Post post = getPostById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            return post;
        }
        return null;
    }

    @Override
    public boolean deletePost(UUID id) {
        return posts.removeIf(post -> post.getId().equals(id));
    }
}
