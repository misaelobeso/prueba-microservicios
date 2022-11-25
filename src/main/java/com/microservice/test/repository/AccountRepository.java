package com.microservice.test.repository;

import com.microservice.test.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
    List<AccountEntity> findByIdCustomer(Integer idCustomer);
}
