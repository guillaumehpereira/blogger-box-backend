package fr.guillaumehpereira.blogger.repositories;

import fr.guillaumehpereira.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByTitleContaining(String title);
    List<Post> findByCategory_Id(UUID categoryId);

}
