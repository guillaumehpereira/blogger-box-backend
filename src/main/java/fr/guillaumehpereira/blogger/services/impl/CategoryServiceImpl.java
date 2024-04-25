package fr.guillaumehpereira.blogger.services.impl;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.repositories.CategoryRepository;
import fr.guillaumehpereira.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category newCategory = new Category(name);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        boolean exists = categoryRepository.existsById(id);
        if (exists) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public List<Category> getAllByName(String name) {
        return categoryRepository.findByName(name);
    }
}
