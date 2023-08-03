package ar.edu.undef.fie.arpyduel.exception;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomResponseException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
