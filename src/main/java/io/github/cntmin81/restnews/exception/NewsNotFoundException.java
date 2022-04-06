package io.github.cntmin81.restnews.exception;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(Long id) {
        super("Could not found news id : " + id);
    }
}
