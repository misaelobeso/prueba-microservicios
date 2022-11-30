package com.microservice.test.repository;

import com.microservice.test.entity.TransactionTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends CrudRepository<TransactionTypeEntity, Integer> {
    Optional<TransactionTypeEntity> findByTypeAndState(String type, Boolean state);
}
