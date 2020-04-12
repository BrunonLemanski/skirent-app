package pl.leman.rentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.leman.rentapi.model.Rent;
import pl.leman.rentapi.service.MapValidationErrorService;
import pl.leman.rentapi.service.RentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public Iterable<Rent> getAllRents() {
        return rentService.findAllRents();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRent(@Valid @RequestBody Rent rent, @RequestParam Integer clientId, @RequestParam String qrCode, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);

        if(errorMap != null) {
            return errorMap;
        }

       Rent rent1 =  rentService.addNewRent(rent, clientId, qrCode);

        return new ResponseEntity<>(rent1, HttpStatus.CREATED);
    }
}
