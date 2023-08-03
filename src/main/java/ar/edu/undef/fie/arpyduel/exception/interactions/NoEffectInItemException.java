package ar.edu.undef.fie.arpyduel.exception.interactions;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NoEffectInItemException extends InternalServerErrorException {
    public NoEffectInItemException() {
        super("No effect in item");
    }

}
