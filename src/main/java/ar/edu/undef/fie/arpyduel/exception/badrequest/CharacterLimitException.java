package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CharacterLimitException extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(CharacterLimitException.class);

    public CharacterLimitException() {
        super("Character limit reached");
        logger.warn("Character limit reached");
    }

}
