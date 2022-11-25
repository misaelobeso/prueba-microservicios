package com.microservice.test.service;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.entity.PersonEntity;
import com.microservice.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Iterable<PersonEntity> findAll() {
        return this.personRepository.findAll();
    }

    public Optional<PersonEntity> findById(Integer id) {
        return this.personRepository.findById(id);
    }

    public String getPersonNameById(Integer id) {
        Optional<PersonEntity> personEntity = this.personRepository.findById(id);

        return personEntity.isPresent() ? personEntity.get().getName() : GenericConstant.DEFAULT_STRING;
    }

    @Transactional(rollbackOn = { Exception.class })
    public PersonEntity save (PersonEntity personEntity) {
        return this.personRepository.save(personEntity);
    }
}
