package com.microservice.test.application;

import com.microservice.test.entity.PersonEntity;
import com.microservice.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/get")
    public Iterable<PersonEntity> get() {
        return this.personService.findAll();
    }
}
