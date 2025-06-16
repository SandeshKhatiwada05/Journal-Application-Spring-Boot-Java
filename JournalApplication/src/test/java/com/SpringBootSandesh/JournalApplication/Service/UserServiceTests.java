package com.SpringBootSandesh.JournalApplication.Service;

import com.SpringBootSandesh.JournalApplication.Repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testAdd(){
        assertEquals(4, 3+1);
        assertTrue(8>3);
    }


    @Test
    public void checkUserPresent(){
        assertNotNull(userRepository.findByUsername("123"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "10, 5, 15",
            "5, 5, 9"
    })
    public void parametrized(int a, int b, int Expected){
        assertEquals(a + b, Expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3,
            10, 5, 15,
            5, 5, 9
    })
    public void valueSource(int a, int b, int Expected){
        assertEquals(a + b, Expected);
    }
}
