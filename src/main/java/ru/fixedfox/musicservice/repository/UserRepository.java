package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
}
