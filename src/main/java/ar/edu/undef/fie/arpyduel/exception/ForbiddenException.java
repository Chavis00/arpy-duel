package ar.edu.undef.fie.arpyduel.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomResponseException{
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
