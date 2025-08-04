package com.example.Journal.app.service;

import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.entity.User;
import com.example.Journal.app.repository.JournalEntryRepository;
import com.example.Journal.app.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository ;
    public void SaveEntry(User user){
        userRepository.save(user);
    }
    public List<User> GetAll(){
        return userRepository.findAll();
    }
    public Optional<User> FindById(ObjectId id){
        return userRepository.findById(id) ;
    }
    public void DeleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
