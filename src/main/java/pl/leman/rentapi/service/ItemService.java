package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.exceptions.ObjectNotFoundInDb;
import pl.leman.rentapi.model.Item;
import pl.leman.rentapi.repository.ItemRepository;
import pl.leman.rentapi.repository.ItemsCategoryRepository;


@Service
public class ItemService {

    @Autowired
    private ItemsCategoryRepository itemsCategoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Item addNewItem(Item item, Long categoryId) {

        return itemsCategoryRepository.findById(categoryId)
                .map(itemsCategory -> {
                    item.setItemCategory(itemsCategory);
                    return itemRepository.save(item);
                }).orElseThrow(() -> new ObjectNotFoundInDb("Brak obiektu w bazie"));
    }

    public Item updateItem(Item updatedItem, String code) {
        Item item1 = itemRepository.findByQrCode(code);
        String qr = item1.getQrCode();

        item1 = updatedItem;
        item1.setQrCode(qr);


        return itemRepository.save(item1);
    }
}