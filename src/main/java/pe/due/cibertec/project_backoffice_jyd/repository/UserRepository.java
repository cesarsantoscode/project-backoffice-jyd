package pe.due.cibertec.project_backoffice_jyd.repository;

import org.springframework.data.repository.CrudRepository;
import pe.due.cibertec.project_backoffice_jyd.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
