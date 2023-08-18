package com.nutri.nutri.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Person")
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    PersonService PersonService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createNew(@RequestBody Person person) {
        return PersonService.createNew(person);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPerson() {
        return PersonService.getAllPerson();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return PersonService.deleteById(id);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) {
        return PersonService.updatePerson(person);
    }

}
