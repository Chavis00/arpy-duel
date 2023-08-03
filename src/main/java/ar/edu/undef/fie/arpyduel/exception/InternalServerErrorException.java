package ar.edu.undef.fie.arpyduel.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends CustomResponseException{
    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
