package fr.guillaumehpereira.blogger.controllers.handlers;

import fr.guillaumehpereira.blogger.exceptions.CategoryNameAlreadyExistsException;
import fr.guillaumehpereira.blogger.exceptions.CategoryNotFoundByIdException;
import fr.guillaumehpereira.blogger.exceptions.PostNotFoundByIdException;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    @ExceptionHandler({
            CategoryNotFoundByIdException.class,
            PostNotFoundByIdException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex){
        LOGGER.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler({
            CategoryNameAlreadyExistsException.class
    })
    public ResponseEntity<String> handleBadRequestException(Exception ex){
        LOGGER.warn("[BAD REQUEST] {}", ex.getMessage());
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}
