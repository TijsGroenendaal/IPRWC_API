package groenendaal.tijs.iprwc_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NameAlreadyInUseException extends RuntimeException{

    public NameAlreadyInUseException(String name) {
        super("The Email '" + name + "' Is Already In Use");
    }

}
