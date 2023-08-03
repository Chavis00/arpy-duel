package ar.edu.undef.fie.arpyduel.exception.notfound;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArmorNotFoundException extends NotFoundException {
    private static final Logger logger = LoggerFactory.getLogger(ArmorNotFoundException.class);

    public ArmorNotFoundException(Long id) {
        super("Armor with id " + id + " not found");
        logger.info("Armor with id " + id + " not found");
    }

}
