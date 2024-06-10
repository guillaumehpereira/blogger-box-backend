package fr.guillaumehpereira.blogger.services.impl;

import fr.guillaumehpereira.blogger.exceptions.CategoryNameAlreadyExistsException;
import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
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
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(String name) throws CategoryNameAlreadyExistsException {
        if (!categoryRepository.findByName(name).isEmpty()) throw new CategoryNameAlreadyExistsException(name);
        Category newCategory = new Category(name);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException {
        Category category = getById(id);
        if (!categoryRepository.findByName(name).isEmpty()) throw new CategoryNameAlreadyExistsException(name);
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(UUID id) throws CategoryNotFoundByIdException {
        //Renvoie l'exeception
        getById(id);
        categoryRepository.deleteById(id);
    }
    @Override
    public List<Category> getAllByName(String name) {
        return categoryRepository.findByName(name);
    }
}
