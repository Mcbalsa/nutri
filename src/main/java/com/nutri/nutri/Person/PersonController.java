package com.nutri.nutri.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/Person")
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("Person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createNew(@RequestBody Person person) {
        return personService.createNew(person);
    }

    @GetMapping("GetAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("Delete")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteById(@RequestParam String id) {
        return personService.deleteById(id);
    }

    @PostMapping("Update")
    public Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @PostMapping("Login")
    @ResponseStatus(HttpStatus.OK)
    public Person login(@RequestBody Person person) {
        return personService.login(person);
    }

}
