package com.example.Journal.app.controller;


import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.entity.User;
import com.example.Journal.app.service.JournalEntryService;
import com.example.Journal.app.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JavaEntryController {
    @Autowired
    JournalEntryService journalEntryService ;

    @Autowired
    UserService userService ;
    @GetMapping("/{userName}")
    public ResponseEntity<?> getall(@PathVariable String userName){
        User user = userService.findByUserName(userName) ;
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
    @PostMapping("/{userName}")
    public  ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
             journalEntryService.SaveEntry(myEntry,userName); ;
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED) ;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
    }
    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getRecord(@PathVariable ObjectId myid){
       Optional<JournalEntry> journalEntry= journalEntryService.FindById(myid);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK) ;
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
    @DeleteMapping("/id/{userName}/{reqId}")
    public ResponseEntity<?> deleteRecord(@PathVariable  ObjectId reqId,@PathVariable String userName){
        journalEntryService.DeleteById(reqId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   @PutMapping("/id/{userName}/{reqId}")
   public JournalEntry updateRecord(@PathVariable  ObjectId reqId,@RequestBody JournalEntry newEntry,@PathVariable String userName){
        JournalEntry old   = journalEntryService.FindById(reqId).orElse( null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.SaveEntry(old);
        return old ;
    }

}
