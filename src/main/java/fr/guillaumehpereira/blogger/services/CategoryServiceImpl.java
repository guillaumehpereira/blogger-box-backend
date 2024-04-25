package fr.guillaumehpereira.blogger.services;

import fr.guillaumehpereira.blogger.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();

    public CategoryServiceImpl() {
        categories.add(new Category(UUID.randomUUID(), "my first category"));
        categories.add(new Category(UUID.randomUUID(), "my second category"));
        categories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(UUID id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(UUID.randomUUID(), name);
        categories.add(newCategory);
        return newCategory;
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return category;
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return categories.removeIf(category -> category.getId().equals(id));
    }
}
