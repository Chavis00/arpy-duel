package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEquippedArmorException extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(NoEquippedArmorException.class);

    public NoEquippedArmorException() {
        super("Character has no equipped armor");
        logger.warn("Character has no equipped armor");
    }

}
