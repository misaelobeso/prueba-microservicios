package com.microservice.test.repository;

import com.microservice.test.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    @Override
    Optional<CustomerEntity> findById(Integer integer);
}
