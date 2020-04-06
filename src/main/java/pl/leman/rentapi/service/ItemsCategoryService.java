package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.exceptions.category.ItemsCategoryIdException;
import pl.leman.rentapi.model.ItemsCategory;
import pl.leman.rentapi.repository.ItemsCategoryRepository;

import java.util.Optional;

@Service
public class ItemsCategoryService {

    @Autowired
    private ItemsCategoryRepository itemsCategoryRepository;

    /**
     * Adding new category.
     * @param itemsCategory
     * @return saved object to enity or throw exception.
     */
    public ItemsCategory addNewCategory(ItemsCategory itemsCategory) {
        try{
            itemsCategory.setCategoryName(itemsCategory.getCategoryName().toLowerCase());


            return itemsCategoryRepository.save(itemsCategory);

        }catch (Exception e) {
            throw new ItemsCategoryIdException("Kategoria " + itemsCategory.getCategoryName().toUpperCase() + " już istnieje");
        }
    }

    /**
     * Getting all category from db.
     * @return list of objects.
     */
    public Iterable<ItemsCategory> findAllCategory() {
        return itemsCategoryRepository.findAll();
    }


    /**
     * Getting category by name.
     * @param category put name category to find.
     * @return category object or throw exception.
     */
    public ItemsCategory findCategoryByName(String category) {

        ItemsCategory itemsCategory = itemsCategoryRepository.findItemsCategoryByCategoryName(category);

        if(itemsCategory == null) {
            throw new ItemsCategoryIdException("Kategoria " + itemsCategory.getCategoryName().toUpperCase() + " nie istnieje");
        }

        return itemsCategory;
    }

    /**
     * Deleting category from database by name.
     * @param category name.
     */
    public void deleteCategoryByName(String category) {

        ItemsCategory itemsCategory = itemsCategoryRepository.findItemsCategoryByCategoryName(category);

        if(itemsCategory == null) {
            throw new ItemsCategoryIdException("Kategoria " + category.toUpperCase() + " nie istnieje");
        }

        itemsCategoryRepository.delete(itemsCategory);
    }

    /**
     * Getting category from database by id.
     * @param id unique id.
     * @return object of ItemsCategory.
     */
    public Optional<ItemsCategory> findCategoryById(Integer id) {
        Long idLong = Long.valueOf(id);
        Optional<ItemsCategory> itemsCategory = itemsCategoryRepository.findById(idLong);

        if(itemsCategory == null) {
            throw new ItemsCategoryIdException("Kategoria z ID = " + id + " nie istnieje");
        }

        return itemsCategory;
    }
}
