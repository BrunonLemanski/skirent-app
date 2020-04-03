package pl.leman.rentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.leman.rentapi.model.ItemsCategory;
import pl.leman.rentapi.service.ItemsCategoryService;
import pl.leman.rentapi.service.MapValidationErrorService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class ItemsCategoryController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ItemsCategoryService itemsCategoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@Valid @RequestBody ItemsCategory itemsCategory, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if(errorMap != null) {
            return errorMap;
        }

        ItemsCategory itemsCategory1 = itemsCategoryService.addNewCategory(itemsCategory);

        return new ResponseEntity<>(itemsCategory1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<ItemsCategory> getAllCategory() {
       return itemsCategoryService.findAllCategory();
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String category) {

        ItemsCategory itemsCategory = itemsCategoryService.findCategoryByName(category);

        return new ResponseEntity<>(itemsCategory, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getCategoryById(@RequestParam Integer id) {
        Optional<ItemsCategory> itemsCategory = itemsCategoryService.findCategoryById(id);

        return new ResponseEntity<>(itemsCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{category}")
    public ResponseEntity<?> deleteCategory(@PathVariable String category) {
        itemsCategoryService.deleteCategoryByName(category);

        return new ResponseEntity<>("Kategoria " + category.toUpperCase() + " została usunięta", HttpStatus.OK);
    }
}
