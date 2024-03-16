package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    public Authority findByAuthority(String authorityName);

}
