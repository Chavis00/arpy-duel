package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import ar.edu.undef.fie.arpyduel.exception.CustomResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoWeaponEquippedException extends BadRequestException {
    private static final Logger logger = LoggerFactory.getLogger(NoWeaponEquippedException.class);

    public NoWeaponEquippedException() {
        super("You must equip a weapon before equip it");
        logger.warn("You must equip a weapon before equip it");
    }

}
