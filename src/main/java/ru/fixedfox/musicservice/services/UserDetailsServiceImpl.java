package ru.fixedfox.musicservice.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.NewUserRegistrationDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.repository.AuthorityRepository;
import ru.fixedfox.musicservice.repository.UserRepository;

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
}
