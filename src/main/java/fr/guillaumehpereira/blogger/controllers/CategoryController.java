package fr.guillaumehpereira.blogger.controllers;

import fr.guillaumehpereira.blogger.models.Category;
import fr.guillaumehpereira.blogger.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Retrieves all categories available in the blog")
    public List<Category> getAllCategories(@RequestParam String name) {
        return name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllByName(name);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID", description = "Retrieves a single category by its ID")
    public Category getCategoryById(@Parameter(description = "ID of the category to retrieve") @PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category in the blog")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.create(category.getName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category", description = "Updates the name of an existing category by ID")
    public Category updateCategory(@Parameter(description = "ID of the category to update") @PathVariable UUID id, @RequestBody Category category) {
        return categoryService.updateName(id, category.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category", description = "Deletes an existing category by ID")
    public void deleteCategory(@Parameter(description = "ID of the category to delete") @PathVariable UUID id) {
        categoryService.deleteById(id);
    }
}
