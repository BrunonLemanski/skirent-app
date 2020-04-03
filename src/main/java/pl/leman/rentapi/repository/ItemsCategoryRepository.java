package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.ItemsCategory;

@Repository
public interface ItemsCategoryRepository extends CrudRepository<ItemsCategory, Long> {

    ItemsCategory findItemsCategoryByCategoryName(String categoryName);
}
