package ru.fixedfox.musicservice.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.*;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.repository.AuthorityRepository;
import ru.fixedfox.musicservice.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found",  username)));
        return user;
    }

    public GetUserCreatorsDto loadUserWithCreatorsByUsername(String username) {
        var userDto = new GetUserCreatorsDto();
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found",  username)));
        userDto.setName(user.getName());
        userDto.setCreators(user.getCreators());
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

    public void changeUserAuthority(User user,String newAuthority) {
        if (!user.isAuthorityName(newAuthority)) {
            user.addAuthority(authorityRepository.findByAuthority(newAuthority));
            userRepository.save(user);
        }
    }

    public void changeNameOfUser(EditNameOfUserDto user) {
        var userFromBase = userRepository.findByUsername(user.getUserName()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found",  user.getUserName())));
        userFromBase.setName(user.getName());
        userRepository.save(userFromBase);

    }

    public void changePassword(EditPasswordDto user) {
        var userFromBase = userRepository.findByUsername(user.getUserName()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found",  user.getUserName())));
            userFromBase.setPassword(user.getNewPassword());
            userRepository.save(userFromBase);
    }

    public GetUserMainPageDto getUserForMainPageByUsename(String username) {
        var userDto = new GetUserMainPageDto();
        var userFromBase =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)));
        userDto.setName(userFromBase.getName());
        userDto.setAuthorities(userFromBase.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet()));
        return userDto;
    }
}
