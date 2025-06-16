package com.SpringBootSandesh.JournalApplication.Service;

import com.SpringBootSandesh.JournalApplication.Entity.JournalEntity;
import com.SpringBootSandesh.JournalApplication.Entity.UserEntity;
import com.SpringBootSandesh.JournalApplication.Repository.JournalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Autowired
    UserService userService;

    //Just directly save the entry
    public void saveEntry(JournalEntity journal) {
        journalRepository.save(journal);
    }

    @Transactional
    public void writeJournal(JournalEntity journal, String user) {
        UserEntity username = userService.findByUserName(String.valueOf(user));
        JournalEntity save = journalRepository.save(journal);
        username.getJournalEntities().add(save);//we add journal entry to username we call
        userService.saveUsers(username);//save in database using userService
    }



    public List<JournalEntity> readJournal() {
        return journalRepository.findAll();
    }

    public JournalEntity readAJournal(Long id) {
        return journalRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteJournal(Long id, String username) {
        UserEntity userEntity = userService.findByUserName(username);//extract username from this
        boolean remover= userEntity.getJournalEntities().removeIf(x-> x.getId().equals(id));//check journal id of a user
        if(remover){
        userService.saveUsers(userEntity);//save the user that has removed journal from it
        journalRepository.deleteById(id);//delete the actual journal also
        return true;
        }
        return false;
    }


    @Transactional
    public boolean updateJournal(Long id, JournalEntity journal) {
        // Check if the journal entry exists
        try {
            JournalEntity existingJournal = journalRepository.findById(id).orElse(null);

            if (existingJournal != null) {
                // Update fields if they are not null in the incoming journalEntity
                existingJournal.setTitle(journal.getTitle());
                if (journal.getDescription() != null) {
                    existingJournal.setDescription(journal.getDescription());
                }

                // Save the updated entity back to the repository
                journalRepository.save(existingJournal);
                return true; // Indicate successful update
            } else {
                return false; // Indicate the entry was not found
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: " + e);
        }
    }


}
