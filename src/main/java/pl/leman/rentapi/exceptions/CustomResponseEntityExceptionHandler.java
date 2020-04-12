package pl.leman.rentapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.leman.rentapi.exceptions.category.ItemCategoryIdExceptionResponse;
import pl.leman.rentapi.exceptions.category.ItemsCategoryIdException;
import pl.leman.rentapi.exceptions.client.ClientException;
import pl.leman.rentapi.exceptions.client.ClientExceptionResponse;
import pl.leman.rentapi.exceptions.item.ItemQrCodeException;
import pl.leman.rentapi.exceptions.rent.RentException;
import pl.leman.rentapi.exceptions.rent.RentExceptionRespone;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handlerItemCategoryId(ItemsCategoryIdException ex) {
        ItemCategoryIdExceptionResponse response = new ItemCategoryIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> hanlderItemQrCode(ItemQrCodeException ex) {
        ItemCategoryIdExceptionResponse response = new ItemCategoryIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> hanlderClientException(ClientException ex) {
        ClientExceptionResponse response = new ClientExceptionResponse(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handlerRentException(RentException ex) {
        RentExceptionRespone response = new RentExceptionRespone(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
