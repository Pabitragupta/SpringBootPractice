package com.example.journal.controller;

import com.example.journal.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private HashMap<Long, JournalEntity> journalEntries = new HashMap<>();

    //Check all the data
    @GetMapping
    public List<JournalEntity> getAll(){

        return new ArrayList<>(journalEntries.values());
    }

    //used to Insert the new data
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity myEntity){
        journalEntries.put(myEntity.getId(), myEntity);
        return true;
    }

    // Used to get a specific id details
    @GetMapping("id/{myId}")
    public JournalEntity getjournalEntiityId(@PathVariable Long myId){

        return journalEntries.get(myId);
    }

    //used to delete the data
    @DeleteMapping("id/{myId}")
    public JournalEntity deleteTheJournalEntry(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    //used to update the data
    @PutMapping("id/{myId}")
    public JournalEntity updateJournalEntity(@PathVariable Long myId, @RequestBody JournalEntity myEntity){
        return journalEntries.put(myId, myEntity);
    }
}
