package com.microservice.test.service;

import com.microservice.test.entity.AccountTypeEntity;
import com.microservice.test.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public Optional<AccountTypeEntity> findById(Integer id) {
        return this.accountTypeRepository.findById(id);
    }

    public Optional<AccountTypeEntity> findByName(String name) {
        return this.accountTypeRepository.findByName(name);
    }

    public Iterable<AccountTypeEntity> findAll() {
        return this.accountTypeRepository.findAll();
    }
}
