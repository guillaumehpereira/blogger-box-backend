package fr.guillaumehpereira.blogger.exceptions;

import java.util.UUID;

public class PostNotFoundByIdException extends Exception {
    public PostNotFoundByIdException(UUID uuid) {
        super("Post not found for this id : " + uuid);
    }
}
