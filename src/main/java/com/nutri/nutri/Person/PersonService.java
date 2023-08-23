package com.nutri.nutri.Person;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    Person createNew(Person person);
    Person updatePerson(Person person);
    List<Person> getAllPerson();
    Boolean deleteById(String id);
    Person login(Person person);
}
