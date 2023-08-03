package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChallengerCanNotAcceptDuel extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(ChallengerCanNotAcceptDuel.class);

    public ChallengerCanNotAcceptDuel(Long duelId) {
        super("Character can not accept duel with id: " + duelId + " because is the challenger");
        logger.warn("Character can not accept duel with id: " + duelId + " because is the challenger");
    }

}
