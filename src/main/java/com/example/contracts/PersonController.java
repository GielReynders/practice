package com.example.contracts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    private static final Person person = new Person("1", "Giel", "Reynders", 27);

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return person;
    }

    @GetMapping
    public ResponseEntity<Person> getPersons() {
        return ResponseEntity.ok(person);
    }
}
