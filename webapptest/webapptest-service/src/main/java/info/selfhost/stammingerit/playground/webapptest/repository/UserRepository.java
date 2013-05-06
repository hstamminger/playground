package info.selfhost.stammingerit.playground.webapptest.repository;

import info.selfhost.stammingerit.playground.webapptest.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>, UserRepositoryCustom {
}
