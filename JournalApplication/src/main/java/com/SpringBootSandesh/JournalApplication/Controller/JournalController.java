package com.SpringBootSandesh.JournalApplication.Controller;

import com.SpringBootSandesh.JournalApplication.Entity.JournalEntity;
import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Service.JournalService;
import com.SpringBootSandesh.JournalApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalController {

    // Dependency injection via constructor
    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @Autowired
    private UserService userService;

    // Get all journals of a user via SecurityContext
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUserName(username);
        List<JournalEntity> journals = userEntity.getJournalEntities();
        if (journals != null && !journals.isEmpty()) {//dont need to be checked by the way
            return new ResponseEntity<>(journals, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



//     Get journal by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUserName(username);

        // Stream and filter journal entities by matching the id
        List<JournalEntity> filteredJournals = userEntity.getJournalEntities()
                .stream()
                .filter(x -> x.getId().equals(id))
                .toList();

        if (!filteredJournals.isEmpty()) {
            return new ResponseEntity<>(filteredJournals.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //another approach for getting a user id can be
    //retrieving a user's journal, and then retrieving Journal via Id
    // Get journal by ID


    // Create a new journal entry
    @PostMapping
    public ResponseEntity<?> postJournal(@RequestBody JournalEntity journal) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String user = authentication.getName();
            journalService.writeJournal(journal, user);
            return new ResponseEntity<>("Journal Uploaded", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Delete journal of a user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            boolean isDeleted = journalService.deleteJournal(id, username);
            if (isDeleted) {
                return new ResponseEntity<>("Deleted Successfully" ,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Journal not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a journal entry
    @PutMapping("/{id}")
    public ResponseEntity<?> putJournal(@PathVariable Long id, @RequestBody JournalEntity newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = userService.findByUserName(username);

        // Stream and filter journal entities by matching the id
        List<JournalEntity> filteredJournals = userEntity.getJournalEntities()
                .stream()
                .filter(x -> x.getId().equals(id))
                .toList();

        //filteredJournal serves no purpose but to check if the Journal with the Id is present

        if (!filteredJournals.isEmpty()) {
            JournalEntity old = journalService.readAJournal(id);
            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().equals("") ? newEntry.getDescription() : old.getDescription());
                journalService.saveEntry(old);
                return new ResponseEntity<>("Updated", HttpStatus.OK);
               
            } else {
                return new ResponseEntity<>("Search Failed", HttpStatus.BAD_REQUEST);
            }
//       JournalEntity old = journalService.readAJournal(id);


        }
        else{
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }


}
