package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.NewCreatorDto;
import ru.fixedfox.musicservice.entity.Creator;
import ru.fixedfox.musicservice.repository.CreatorRepository;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public CreatorService(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    public Creator getCreatorById(Long id) {
        return creatorRepository.getReferenceById(id);
    }

    public void addNewCreator(NewCreatorDto newCreatorDto) {
        var creator = new Creator();
        creator.setCreatorName(newCreatorDto.getCreatorName());
        creator.setUser(newCreatorDto.getUser());
        creatorRepository.save(creator);
    }
}
