package com.example.Journal.app.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
   private ObjectId id ;
    @Indexed(unique = true)
    @NonNull
    @NotBlank(message = "Username cannot be blank")
    private String userName ;
    @NonNull
    private String password ;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
}