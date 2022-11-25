package com.microservice.test.repository;

import com.microservice.test.entity.AccountTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountTypeEntity, Integer> {
    Optional<AccountTypeEntity> findByName(String name);
}
