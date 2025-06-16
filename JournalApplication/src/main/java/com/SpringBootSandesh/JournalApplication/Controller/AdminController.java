package com.SpringBootSandesh.JournalApplication.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //Autowiring via Constructor
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    //View All Users
    @GetMapping("/all-users")
    public ResponseEntity<?> viewAllUsers() {
        List<UserEntity> allUsers = userService.findAllUsers();
        if (allUsers != null && !allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
