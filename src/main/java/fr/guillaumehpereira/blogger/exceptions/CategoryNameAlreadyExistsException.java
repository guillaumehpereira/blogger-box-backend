package fr.guillaumehpereira.blogger.exceptions;

import java.util.UUID;

public class CategoryNameAlreadyExistsException extends Exception {
    public CategoryNameAlreadyExistsException(String name) {
        super("A category has already this name : " + name);
    }
}
