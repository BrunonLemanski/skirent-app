package pl.leman.rentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.leman.rentapi.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
