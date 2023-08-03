package ar.edu.undef.fie.arpyduel.exception.notfound;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassNotFoundExceptionById extends NotFoundException {
    private static final Logger logger = LoggerFactory.getLogger(ClassNotFoundExceptionById.class);

    public ClassNotFoundExceptionById(Long id) {
        super("Class with ID " + id + " was not found.");
        logger.info("Class with ID " + id + " was not found.");
    }

}
