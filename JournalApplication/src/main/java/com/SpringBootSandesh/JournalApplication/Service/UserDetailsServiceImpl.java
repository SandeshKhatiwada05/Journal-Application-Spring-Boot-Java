package com.SpringBootSandesh.JournalApplication.Service;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return org.springframework.security.core.userdetails.User.builder().
                    username(userEntity.getUsername()).
                    password(userEntity.getPassword()).
                    roles(userEntity.getRoles().toArray(new String[0])).
                    build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
