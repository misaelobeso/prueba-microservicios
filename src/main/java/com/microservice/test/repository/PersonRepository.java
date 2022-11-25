package com.microservice.test.repository;

import com.microservice.test.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {
    List<PersonEntity> findByName(String nombre);

    @Override
    Optional<PersonEntity> findById(Integer id);
}
