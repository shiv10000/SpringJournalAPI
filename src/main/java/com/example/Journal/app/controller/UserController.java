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

import java.rmi.MarshalledObject;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.GetAll() ;
    }
    @PostMapping
    public void createUser(@RequestBody  User user){
        userService.SaveEntry(user);
    }
    @PutMapping("/{username}")
     public void updateUser(@RequestBody  User user,@PathVariable String username ){
        User userInDb = userService.findByUserName(username);
        if(userInDb != null){
             userInDb.setUserName(user.getUserName());
             userInDb.setPassword(user.getPassword());
             userService.SaveEntry(userInDb);
        }
    }


}
