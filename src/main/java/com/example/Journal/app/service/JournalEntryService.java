package com.example.Journal.app.service;

import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository  journalEntryRepository ;
    public void SaveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> GetAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> FindById(ObjectId id){
        return journalEntryRepository.findById(id) ;
    }
    public void DeleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
     }

}
