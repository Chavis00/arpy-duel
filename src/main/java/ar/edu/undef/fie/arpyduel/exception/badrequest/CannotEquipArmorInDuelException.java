package ar.edu.undef.fie.arpyduel.exception.badrequest;

import ar.edu.undef.fie.arpyduel.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotEquipArmorInDuelException extends BadRequestException {
    public CannotEquipArmorInDuelException() {
        super("Cannot equip armor in duel");
    }

}
