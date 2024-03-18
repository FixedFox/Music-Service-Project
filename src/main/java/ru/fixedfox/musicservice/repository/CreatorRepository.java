package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Creator;

import java.util.Optional;
import java.util.Set;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    public Set<Creator> findByCreatorNameContainingIgnoreCase(String name);

    public Set<Creator> findByUser_Id(Long userId);

    public Optional<Creator> findById(Long creatorId);

}
