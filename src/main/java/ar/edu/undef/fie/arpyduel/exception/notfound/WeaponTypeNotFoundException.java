package ar.edu.undef.fie.arpyduel.exception.notfound;

import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import ar.edu.undef.fie.arpyduel.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeaponTypeNotFoundException extends NotFoundException {
    private static final Logger logger = LoggerFactory.getLogger(WeaponTypeNotFoundException.class);

    public WeaponTypeNotFoundException(Long id) {
        super("Weapon type with id: " + id + " not found");
        logger.info("Weapon type with id: " + id + " not found");
    }

}
