package pl.leman.rentapi.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemsCategoryIdException extends RuntimeException {

    public ItemsCategoryIdException(String message) {
        super(message);
    }
}
