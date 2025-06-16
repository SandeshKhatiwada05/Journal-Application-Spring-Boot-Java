package com.SpringBootSandesh.JournalApplication.Service;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUsers(UserEntity users) {
        userRepository.save(users);
    }

    // Save user with encoded password
    public void saveNewUsers(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));  // Encode the password
        userEntity.setRoles(List.of("USER"));
        userRepository.save(userEntity);
        System.out.println("User  created: " + userEntity.getUsername()); // Add logging
    }

    public UserEntity findByUserName(@RequestBody String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void updateUsers(UserEntity userEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //get information of user from context holder
        String username = authentication.getName();

        // Check if the journal entry exists
        UserEntity existingUsers = userRepository.findByUsername(username);

            // Update fields if they are not null in the incoming journalEntity
            if (userEntity.getUsername() != null) {//no need as authenticated name & pw can never be null
                existingUsers.setUsername(userEntity.getUsername());
            }
            if (userEntity.getPassword() != null) {
                existingUsers.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            }

            // Save the updated entity back to the repository
            userRepository.save(existingUsers);
        }

        @Transactional
        public void deleteUser(String user) {
            userRepository.deleteByUsername(user);
        }

}

