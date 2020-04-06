package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.exceptions.ObjectNotFoundInDb;
import pl.leman.rentapi.exceptions.item.ItemQrCodeException;
import pl.leman.rentapi.model.Item;
import pl.leman.rentapi.repository.ItemRepository;
import pl.leman.rentapi.repository.ItemsCategoryRepository;

import java.util.Optional;


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

    public Iterable<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item findByQrCode(String qrCode) {
        Item item = itemRepository.findByQrCode(qrCode);

        if(item == null) {
            throw new ItemQrCodeException("Brak '" + qrCode + "' w bazie danych");
        }

        return item;
    }

    public void deleteItemByQrCode(String qr) {

        Item item = itemRepository.findByQrCode(qr);

        if(item == null) {
            throw new ItemQrCodeException("Brak wpisu w bazie danych");
        }

        itemRepository.delete(item);
    }

    public Optional<Item> findItemById(Integer id) {
        Long idLong = Long.valueOf(id);
        Optional<Item> item = itemRepository.findById(idLong);

        if(item == null) {
            throw new ItemQrCodeException("Brak wpisu w bazie");
        }

        return item;
    }
}