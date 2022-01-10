package io.agileintelligence.ppmtool.repository;

import io.agileintelligence.ppmtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User getById(Long id);

    Optional<User> findById(Long id);
}
