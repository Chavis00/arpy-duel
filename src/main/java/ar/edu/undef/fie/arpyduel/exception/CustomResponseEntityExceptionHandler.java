package ar.edu.undef.fie.arpyduel.exception;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    // Manage CustomResponseException
    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(CustomResponseException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(ex.getStatus().toString(), ex.getMessage()));
    }

}