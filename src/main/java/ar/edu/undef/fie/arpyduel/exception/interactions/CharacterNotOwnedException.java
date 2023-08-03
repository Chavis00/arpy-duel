package ar.edu.undef.fie.arpyduel.exception.interactions;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CharacterNotOwnedException extends ForbiddenException {
    private static final Logger logger = LoggerFactory.getLogger(CharacterNotOwnedException.class);

    public CharacterNotOwnedException() {
        super("Character not owned by user");
        logger.error("Character not owned by user");
    }

}
