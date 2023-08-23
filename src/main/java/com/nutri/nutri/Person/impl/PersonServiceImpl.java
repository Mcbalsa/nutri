package com.nutri.nutri.Person.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutri.nutri.Person.Person;
import com.nutri.nutri.Person.PersonRepository;
import com.nutri.nutri.Person.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person createNew(Person person) {
        return personRepository.save(
                Person.builder()
                        .username(person.getUsername())
                        .password(person.getPassword())
                        .Role(person.getRole())
                        .height(person.getHeight())
                        .weight(person.getWeight())
                        .build()

        );
    }

    @Override
    public Person updatePerson(Person person) {
        Optional<Person> existingPerson = personRepository.findById(person.getId());
        if (existingPerson.isEmpty())
            throw new RuntimeException(String.format("No person found for id %s", person.getId()));
        existingPerson.get().setUsername(person.getUsername());
        existingPerson.get().setPassword(person.getPassword());
        existingPerson.get().setHeight(person.getHeight());
        existingPerson.get().setWeight(person.getWeight());
        return personRepository.save(existingPerson.get());
    }

    @Override
    public List<Person> getAllPerson() {
        List<Person> people = personRepository.findAll();

        return people;
    }

    @Override
    public Boolean deleteById(String id) {
        personRepository.deleteById(id);
        if (personRepository.findById(id).isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Person login(Person person) {
        Optional<Person> existingPerson = personRepository.findByusername(person.getUsername());
        if(existingPerson.isEmpty())
            throw new RuntimeException(String.format("No person found for username %s",person.getUsername()));
        if(!existingPerson.get().getPassword().matches(person.getPassword())) {
            throw new RuntimeException(String.format("Password is incorrect"));
        }
        
        return personRepository.save(existingPerson.get());
        }
}
