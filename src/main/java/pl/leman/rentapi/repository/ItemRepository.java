package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByQrCode(String code);

}
