package fr.guillaumehpereira.blogger.controllers;

import fr.guillaumehpereira.blogger.models.Category;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "API endpoints for managing categories")
public class CategoryController {

    private final List<Category> categories = new ArrayList<>();

    public CategoryController() {
        // Initialize with three random categories
        categories.add(new Category());
        categories.add(new Category());
        categories.add(new Category());
    }
    @GetMapping
    @Operation(summary = "Retrieve all categories", description = "Retrieves all categories available in the blog")
    public List<Category> getAllCategories() {
        return List.of();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by ID", description = "Retrieves a single category by its ID")
    public Category getCategoryById(@Parameter(description = "ID of the category to retrieve") @PathVariable Long id) {
        return new Category();
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category in the blog")
    public Category createCategory(@RequestBody Category category) {
        return category;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category", description = "Updates the name of an existing category by ID")
    public Category updateCategory(@Parameter(description = "ID of the category to update") @PathVariable Long id, @RequestBody Category category) {
        return category;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category", description = "Deletes an existing category by ID")
    public void deleteCategory(@Parameter(description = "ID of the category to delete") @PathVariable Long id) {
    }
}
