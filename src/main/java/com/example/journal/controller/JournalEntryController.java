package com.example.journal.controller;

import com.example.journal.entity.JournalEntity;
import com.example.journal.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryservice;

    @PostMapping
    public JournalEntity addUser(@RequestBody JournalEntity journalEntity) {
        return journalEntryservice.saveUser(journalEntity);
    }
}

