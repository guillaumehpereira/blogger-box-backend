package fr.guillaumehpereira.blogger.controllers;

import fr.guillaumehpereira.blogger.exceptions.CategoryNameAlreadyExistsException;
import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "API endpoints for managing categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Retrieves all categories available in the blog")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam (required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllByName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID", description = "Retrieves a single category by its ID")
    public ResponseEntity<Category> getCategoryById(@Parameter(description = "ID of the category to retrieve") @PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category in the blog")
    public ResponseEntity<Category> createCategory(@RequestBody String categoryName) throws CategoryNameAlreadyExistsException {
        Category newCategory = categoryService.create(categoryName);
        return ResponseEntity
                .created(URI.create("v1/categories/" + newCategory.getId()))
                .body(newCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category", description = "Updates the name of an existing category by ID")
    public ResponseEntity<Category> updateCategory(@Parameter(description = "ID of the category to update") @PathVariable UUID id, @RequestBody String categoryName) throws CategoryNotFoundByIdException, CategoryNameAlreadyExistsException {
        Category updatedCategory = categoryService.updateName(id, categoryName);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category", description = "Deletes an existing category by ID")
    public ResponseEntity<Void> deleteCategory(@Parameter(description = "ID of the category to delete") @PathVariable UUID id) throws CategoryNotFoundByIdException {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
