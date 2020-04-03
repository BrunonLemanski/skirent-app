package pl.leman.rentapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "NotFoundException not found")
public class ObjectNotFoundInDb extends RuntimeException {

    public ObjectNotFoundInDb(String msg) {
        super(msg);
    }

    public ObjectNotFoundInDb(String message, Throwable cause) {
        super(message, cause);
    }
}
