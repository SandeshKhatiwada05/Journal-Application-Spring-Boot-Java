package com.SpringBootSandesh.JournalApplication.Controller;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health")
    public String getHealth() {
        return "Health is All good";
    }

    @PostMapping("/create-user")
    public String postUser (@RequestBody UserEntity userEntity) {
        userService.saveNewUsers(userEntity);
        return "User  Added Successfully";
    }

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> allUsers = userService.findAllUsers();
        if(allUsers!=null)
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        else
            return new ResponseEntity<>("Some Issue in Public Controller", HttpStatus.NOT_FOUND);
    }

}
