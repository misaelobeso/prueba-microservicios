package com.microservice.test.repository;

import com.microservice.test.entity.AccountTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountTypeEntity, Integer> {

}
