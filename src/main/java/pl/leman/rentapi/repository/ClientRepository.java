package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
