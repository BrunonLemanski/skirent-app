package pl.leman.rentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.leman.rentapi.model.Item;
import pl.leman.rentapi.service.ItemService;
import pl.leman.rentapi.service.MapValidationErrorService;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/all")
    public Iterable<Item> getAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("")
    public ResponseEntity<?> getItemByQrCode(@RequestParam String qrCode) {

        Item item = itemService.findByQrCode(qrCode);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("") //TODO: poprawic
    public ResponseEntity<?> getItemById(@RequestParam Integer id) {

        Optional<Item> item = itemService.findItemById(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/add/{category_id}")
    public ResponseEntity<?> addItem(@RequestBody Item item, @PathVariable Long category_id, BindingResult bindingResult) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
        if(errorMap != null) {
            return errorMap;
        }

        Item item1 = itemService.addNewItem(item, category_id);

        return new ResponseEntity<>(item1, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{qr_code}")
    public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable String qr_code, BindingResult bindingResult) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
        if(errorMap != null) {
            return errorMap;
        }

        Item item1 = itemService.updateItem(item, qr_code);

        return new ResponseEntity<>(item1, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteItemByQrCode(@RequestParam String qrCode) {
        Item item = itemService.findByQrCode(qrCode);

        itemService.deleteItemByQrCode(qrCode);

        return new ResponseEntity<>("Przedmiot " + item.getMake() + " " + item.getModel() + " został usunięty.", HttpStatus.OK);
    }
}
