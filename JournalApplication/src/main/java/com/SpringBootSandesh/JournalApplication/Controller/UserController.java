package com.SpringBootSandesh.JournalApplication.Controller;

import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //Constructor used for Autowiring
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //let's not use this as it shows all the records with any username and password
    @GetMapping("/all")
    public List<UserEntity> getUsers(){
        return userService.findAllUsers();
    }

    //To Only get Information and password of a user
    @GetMapping
    public ResponseEntity<?> getUserInfo(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            UserEntity userEntity = userService.findByUserName(userName);

            //Can even Hide password here if necessary
//        userEntity.setPassword("**Hidden**");

            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some Error Occurred while searching", HttpStatus.NO_CONTENT);
        }
    }


    //Post Mapping has been transferred to Public Controller as it can be accessed by everyone

    @PutMapping
    public String putMapping( @RequestBody UserEntity userEntity){
        userService.updateUsers(userEntity);
        return "Updated Successfully";
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(authentication.getName());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

//    @GetMapping("/users")
//    public String getUser(){
//        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
//        return  "Hello " + authentication.getName() +"\n" ;
//
//    }
}
