package groenendaal.tijs.iprwc_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException() {
        super("Invalid JWT token");
    }

}
