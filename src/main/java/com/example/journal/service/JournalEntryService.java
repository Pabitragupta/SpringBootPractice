package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    //Used to return all the data into the database
    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }



    //Used to Add the data into the database
    public void saveEntry(JournalEntity journalEntity, String userName) {
        // Find the user by userName
        User user = userService.findByUserName(userName);

        if (user != null) {
            // Set the user for the journal entry
            journalEntity.setUser(user);
            // Ensure bidirectional relationship
            user.getJournalEntries().add(journalEntity);
            // Save the journal entry in the repository
            journalEntryRepository.save(journalEntity);
            // Save the user to persist the new relationship
            userService.saveEntry(user);
        }
    }

    public void saveEntry(JournalEntity journalEntity) {
            journalEntryRepository.save(journalEntity);
    }


    //used to find the data based on the id
    public Optional<JournalEntity> findById(int id){
        return journalEntryRepository.findById(id);
    }



    //used to delete the data based on the id
    public void deleteById(int id, String userName){
        User user = userService.findByUserName(userName);

        user.getJournalEntries().removeIf(x -> x.getId() == id);
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
