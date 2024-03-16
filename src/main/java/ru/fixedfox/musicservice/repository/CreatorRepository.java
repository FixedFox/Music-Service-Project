package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixedfox.musicservice.entity.Creator;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    public Creator getCreatorByCreatorName(String name);
}
