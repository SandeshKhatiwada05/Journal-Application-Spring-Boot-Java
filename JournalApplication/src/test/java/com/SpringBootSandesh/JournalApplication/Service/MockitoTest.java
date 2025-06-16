package com.SpringBootSandesh.JournalApplication.Service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@Disabled
public class MockitoTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;


    @Mock
    private UserRepository userRepository;


    @Test
    public void mokitoLoadByUserName(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(UserEntity.builder().username("Sandesh").password("JKJKJK").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Sandesh");
        Assertions.assertNotNull(user);
    }
}