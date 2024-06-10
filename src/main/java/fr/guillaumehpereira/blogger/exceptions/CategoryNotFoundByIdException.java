package fr.guillaumehpereira.blogger.exceptions;

import java.util.UUID;

public class CategoryNotFoundByIdException extends Exception {
    public CategoryNotFoundByIdException(UUID uuid) {
        super("Category not found for this id : " + uuid);
    }
}
