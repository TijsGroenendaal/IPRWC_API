package groenendaal.tijs.iprwc_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public <T> EntityNotFoundException(Class<T> entity) {
        super("Entity of Type '" + entity.getSimpleName() + "' Not Found");
    }

}
