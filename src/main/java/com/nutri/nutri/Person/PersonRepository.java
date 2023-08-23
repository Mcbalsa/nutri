package com.nutri.nutri.Person;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PersonRepository extends MongoRepository<Person, String>{
    Optional <Person> findByusername(String username);
}