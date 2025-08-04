package com.example.Journal.app.repository;

import com.example.Journal.app.entity.JournalEntry;
import com.example.Journal.app.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUserName(String userName);
}
