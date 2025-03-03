package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntity saveUser(JournalEntity journalEntity) {
        return journalEntryRepository.save(journalEntity);
    }
}
