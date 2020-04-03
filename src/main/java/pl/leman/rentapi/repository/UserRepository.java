package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
