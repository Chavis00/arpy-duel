package ar.edu.undef.fie.arpyduel.exception.interactions;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmptyQueueException extends InternalServerErrorException {
    private static final Logger logger = LoggerFactory.getLogger(EmptyQueueException.class);

    public EmptyQueueException() {
        super("Cannot find any Character in the queue");
        logger.error("Cannot find any Character in the queue");
    }

}
