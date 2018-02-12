package challenge.code.configuration_manager.api.model.error;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Property already exists!", value = HttpStatus.CONFLICT)
public class PropertyAlreadyExistsException extends RuntimeException {
    public PropertyAlreadyExistsException(DuplicateKeyException e) {
        super(e);
    }
}
