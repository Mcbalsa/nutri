package com.nutri.nutri.Person;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// REMINDER: MongoDB does not support Cascade delete
// RelMongo

@Document("Person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Person {
    @Id
    private String id;

    private String username;
    private String password;
    private int height;
    private int weight;
    private Role Role;

    private List<FoodEntry> foodJournal;
    
}