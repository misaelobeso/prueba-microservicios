package com.microservice.test.repository;

import com.microservice.test.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByIdAccountAndCreatedAtBetween(Integer idAccount,Date startDate, Date endDate);

    Iterable<TransactionEntity> findByState(Boolean state);
}
