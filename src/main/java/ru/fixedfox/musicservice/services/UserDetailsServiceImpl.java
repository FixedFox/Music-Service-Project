package ru.fixedfox.musicservice.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.*;
import ru.fixedfox.musicservice.entity.Creator;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.repository.AuthorityRepository;
import ru.fixedfox.musicservice.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final TrackService trackService;
    private final CreatorService creatorService;

    public UserDetailsServiceImpl(UserRepository userRepository,
                                  AuthorityRepository authorityRepository,
                                  TrackService trackService,
                                  CreatorService creatorService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.trackService = trackService;
        this.creatorService = creatorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
    }

    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
    }

    public GetUserCreatorsDto loadNameWithCreatorsByUsername(String username) {
        var userDto = new GetUserCreatorsDto();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        userDto.setName(user.getName());
        userDto.setCreators(user.getCreators());
        return userDto;
    }

    public GetUserTracksDto loadNameWithTracksByUsername(String username) {
        var userDto = new GetUserTracksDto();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        userDto.setName(user.getName());
        userDto.setTracks(user.getTracks());
        return userDto;
    }

    public void addNewUser(NewUserRegistrationDto newUser) {
        var user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());
        user.addAuthority(authorityRepository.findByAuthority("USER"));
        userRepository.save(user);
    }

    public void changeUserAuthority(User user, String newAuthority) {
        if (!user.isAuthorityName(newAuthority)) {
            user.addAuthority(authorityRepository.findByAuthority(newAuthority));
            userRepository.save(user);
        }
    }

    public void changeNameOfUser(EditNameOfUserDto user) {
        var userFromBase = userRepository.findByUsername(user.getUserName()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", user.getUserName())));
        userFromBase.setName(user.getName());
        userRepository.save(userFromBase);

    }

    public void changePassword(EditPasswordDto user) {
        var userFromBase = userRepository.findByUsername(user.getUserName()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", user.getUserName())));
        userFromBase.setPassword(user.getNewPassword());
        userRepository.save(userFromBase);
    }

    public GetUserMainPageDto getUserForMainPageByUsename(String username) {
        var userDto = new GetUserMainPageDto();
        var userFromBase = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        userDto.setName(userFromBase.getName());
        userDto.setAuthorities(userFromBase.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet()));
        return userDto;
    }

    public AllLibraryTracksDto getAllLibraryTracksByUsername(String username) {
        var userLibraryTracks = new AllLibraryTracksDto();
        var userFromBase = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        userLibraryTracks.setTracks(userFromBase.getLibrarytracks());
        return userLibraryTracks;
    }

    @Transactional
    public void addTrackToMyLibraryById(EditTrackMyLibraryDto trackFromLibrary) {
        var userFromBase = userRepository.findByUsername(trackFromLibrary.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", trackFromLibrary.getUsername())));
        var track = trackService.findTrackById(trackFromLibrary.getTrackId());
        userFromBase.addLibrarytrack(track);
        userRepository.save(userFromBase);
    }

    @Transactional
    public void deleteTrackFromMyLibraryById(EditTrackMyLibraryDto trackFromLibrary) {
        var userFromBase = userRepository.findByUsername(trackFromLibrary.getUsername()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", trackFromLibrary.getUsername())));
        var track = trackService.findTrackById(trackFromLibrary.getTrackId());
        userFromBase.removeLibrarytrack(track);
        userRepository.save(userFromBase);
    }

    public Set<Creator> getSubscriptionsByUsername(String username) {
        var userFromBase = findUserByUsername(username);
        return userFromBase.getSubscribtions();
    }

    @Transactional
    public void addCreatorToUserSubscriptionsById(Long creatorId, String username) {
        var userFromBase = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        var creator = creatorService.findCreatorById(creatorId);
        userFromBase.addSubscription(creator);
        userRepository.save(userFromBase);
    }

    @Transactional
    public void removeCreatorFromUserSubscriptionsById(Long creatorId, String username) {
        var userFromBase = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        var creator = creatorService.findCreatorById(creatorId);
        userFromBase.removeSubscription(creator);
    }
}
