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
        User user = userService.findByUserName(userName); //used to find the user with the help of userName.
        JournalEntity saved = journalEntryRepository.save(journalEntity); //Stored it in a local variable.
        user.getJournalEntries().add(saved); //user is a type of list that is defined in "User" class, and we perform add operation on that arraylist;
        userService.saveEntry(user); //Here we store the new updated arraylist.
    }



    //used to find the data based on the id
    public Optional<JournalEntity> findById(int id){
        return journalEntryRepository.findById(id);
    }



    //used to delete the data based on the id
    public void deleteById(int id){
        journalEntryRepository.deleteById(id);
    }
}
