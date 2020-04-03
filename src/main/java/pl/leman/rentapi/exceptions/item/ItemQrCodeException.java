package pl.leman.rentapi.exceptions.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemQrCodeException extends RuntimeException {

    public ItemQrCodeException(String message) {
        super(message);
    }
}
