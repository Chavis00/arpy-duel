package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FriendLimitException extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(FriendLimitException.class);

    public FriendLimitException() {
        super("Friend limit reached");
        logger.warn("Friend limit reached");
    }

}
