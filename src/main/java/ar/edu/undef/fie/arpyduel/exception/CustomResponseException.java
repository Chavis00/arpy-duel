package ar.edu.undef.fie.arpyduel.exception;

import org.springframework.http.HttpStatus;

public class CustomResponseException extends RuntimeException {
    private final HttpStatus status;

    public CustomResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

