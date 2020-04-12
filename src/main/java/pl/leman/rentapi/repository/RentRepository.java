package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {
}
