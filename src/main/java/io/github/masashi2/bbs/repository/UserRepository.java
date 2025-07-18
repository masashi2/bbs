package io.github.masashi2.bbs.repository;

import io.github.masashi2.bbs.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsById(String id);
}

