package com.example.Journal.app.service;

import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.entity.User;
import com.example.Journal.app.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository  journalEntryRepository ;

    @Autowired
    private UserService userService ;

     public void SaveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName) ;
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.SaveEntry(user);
    }
    public void SaveEntry(JournalEntry journalEntry){
         JournalEntry saved = journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> GetAll(){
         return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> FindById(ObjectId id){
        return journalEntryRepository.findById(id) ;
    }
    public void DeleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName) ;
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.SaveEntry(user);
        journalEntryRepository.deleteById(id);

     }
}
