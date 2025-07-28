package com.example.Journal.app.controller;


import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JavaEntryController {
    @Autowired
    JournalEntryService journalEntryService ;
    @GetMapping
    public List<JournalEntry> getall(){
        return journalEntryService.GetAll() ;
    }
    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.SaveEntry(myEntry); ;
        return true;
    }
    @GetMapping("/id/{myid}")
    public JournalEntry getRecord(@PathVariable ObjectId myid){
        return journalEntryService.FindById(myid).orElse(null);
    }
    @DeleteMapping("/id/{reqId}")
    public boolean deleteRecord(@PathVariable  ObjectId reqId){

        journalEntryService.DeleteById(reqId);
        return true;
    }
    @PutMapping("/id/{reqId}")
    public JournalEntry updateRecord(@PathVariable  ObjectId reqId,@RequestBody JournalEntry newEntry){
        JournalEntry old   = journalEntryService.FindById(reqId).orElse( null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.SaveEntry(old);
        return old ;
    }


}
