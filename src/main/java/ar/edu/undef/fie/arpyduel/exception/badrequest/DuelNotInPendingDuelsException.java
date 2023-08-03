package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuelNotInPendingDuelsException extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(DuelNotInPendingDuelsException.class);

    public DuelNotInPendingDuelsException(Long id) {
        super("Duel with id " + id + " is not in pending duels");
        logger.warn("Duel with id " + id + " is not in pending duels");
    }

}
