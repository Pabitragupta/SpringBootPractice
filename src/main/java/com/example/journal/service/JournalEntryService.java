package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    //Used to return all the data into the database
    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }


    //Used to Add the data into the database
    public JournalEntity saveEntry(JournalEntity journalEntity) {
        return journalEntryRepository.save(journalEntity);
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
