package fr.guillaumehpereira.blogger.repositories;

import fr.guillaumehpereira.blogger.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE c.name = :name")
    List<Category> findByName(String name);
}
