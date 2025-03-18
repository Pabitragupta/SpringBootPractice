package com.example.journal.controller;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.service.JournalEntryService;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryservice;


    @Autowired
    private UserService userService;


    // Get all the data into the database
    @GetMapping("/{userName}")
    public ResponseEntity<List<JournalEntity>> getAllJournalEntriesOfUser(@PathVariable String userName){

        User user = userService.findByUserName(userName); //Here we find the user this is our first priority

        List<JournalEntity> allEntity = user.getJournalEntries();

        if(allEntity != null && !allEntity.isEmpty()){
            return new ResponseEntity<>(allEntity, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Add new Journal into the database
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity journalEntity, @PathVariable String userName) {
        try {
            User user = userService.findByUserName(userName);

            if (user != null) {
                journalEntity.setUser(user);
                journalEntryservice.saveEntry(journalEntity, userName);
                return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
            }
            else {
                // Return NOT_FOUND if user doesn't exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            // Return BAD_REQUEST if any error occurs
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //Get the data using the Id
//    @GetMapping("/id/{id}")
//    public JournalEntity getJournalEntryBYId(@PathVariable int id){
//        return journalEntryservice.findById(id).orElse(null);
//    }



    //Get the data using id and return the status code also
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable int id){
        Optional<JournalEntity> journalEntry = journalEntryservice.findById(id);

        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //Delete the data based on the Id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable int id){
        journalEntryservice.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    //used to update the data into the database
//    @PutMapping("/id/{id}")
//    public ResponseEntity<JournalEntity> updateJournalEntryById(@PathVariable int id, @RequestBody JournalEntity newEntry){
//
////        JournalEntity old = journalEntryservice.findById(id).orElse(null);
////
////        if(old != null){
////            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
////            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
////
////            journalEntryservice.saveEntry(old, user);
////            return new ResponseEntity<>(old, HttpStatus.OK);
////        }
////        else{
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//
//    }
}


