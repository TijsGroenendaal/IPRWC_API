package groenendaal.tijs.iprwc_api.exception;

public class EntityNotFoundException extends RuntimeException {

    public <T> EntityNotFoundException(Class<T> entity) {
        super("Entity of Type '" + entity.getSimpleName() + "' Not Found");
    }

}
