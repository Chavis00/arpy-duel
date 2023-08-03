package ar.edu.undef.fie.arpyduel.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomResponseException{
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
