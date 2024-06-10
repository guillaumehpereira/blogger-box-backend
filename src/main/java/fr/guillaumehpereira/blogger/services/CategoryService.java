package fr.guillaumehpereira.blogger.services;

import fr.guillaumehpereira.blogger.exceptions.CategoryNameAlreadyExistsException;
import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
import fr.guillaumehpereira.blogger.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create(String name) throws CategoryNameAlreadyExistsException;
    Category updateName(UUID id, String name) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException;
    void deleteById(UUID id) throws CategoryNotFoundByIdException;
    List<Category> getAllByName(String name);
}
