package ru.fixedfox.musicservice.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.NewCreatorDto;
import ru.fixedfox.musicservice.entity.Creator;
import ru.fixedfox.musicservice.repository.CreatorRepository;

import java.util.Set;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public CreatorService(CreatorRepository creatorRepository) {
        this.creatorRepository = creatorRepository;
    }

    public Creator getCreatorById(Long id) {
        return creatorRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Creator with id = '%s'", id)));
    }

    public void addNewCreator(NewCreatorDto newCreatorDto) {
        var creator = new Creator();
        creator.setCreatorName(newCreatorDto.getCreatorName());
        creator.setUser(newCreatorDto.getUser());
        creatorRepository.save(creator);
    }

    public Set<Creator> findCreatorsByNameByUserId(String creatorName, Long userId) {
        return creatorRepository.findByCreatorNameByUserId(creatorName, userId);
    }
    public Creator findCreatorById(Long creatorId) {
        return creatorRepository.getReferenceById((creatorId));
    }

    public Set<Creator> findCreatorsByUserIdIsNotInTrackById(Long trackId, Long userId) {
        return creatorRepository.findCreatorsByUserIdIsNotInTrackById(trackId ,userId);
    }
    public Set<Creator> findCreatorsByUserIdIsNotInTracklistById(Long tracklistId, Long userId) {
        return creatorRepository.findCreatorsByUserIdIsNotInTracklistById(tracklistId ,userId);
    }

    public Set<Creator> findCreatorByLibraryUserId(Long userId) {
        return creatorRepository.getCreatorsByLibraryUserId(userId);
    }
}
