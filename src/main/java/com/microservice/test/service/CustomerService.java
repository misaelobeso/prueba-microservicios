package com.microservice.test.service;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.CustomerRequestDto;
import com.microservice.test.entity.CustomerEntity;
import com.microservice.test.entity.PersonEntity;
import com.microservice.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonService personService;

    public Optional<CustomerEntity> findById(Integer id) {
        return this.customerRepository.findById(id);
    }

    public Iterable<CustomerEntity> findAll() {
        return this.customerRepository.findAll();
    }

    public String findCustomerNameById(Integer id) {
        Optional<CustomerEntity> customerEntity = this.findById(id);

        return customerEntity.isPresent() ? this.personService.getPersonNameById(customerEntity.get().getIdPerson()) : GenericConstant.DEFAULT_STRING;
    }

    @Transactional(rollbackOn = { Exception.class })
    public CustomerEntity save (CustomerEntity customerEntity) {
        return this.customerRepository.save(customerEntity);
    }

    @Transactional(rollbackOn = { Exception.class })
    public void saveCustomerAndPerson (CustomerRequestDto customerRequestDto) {
        Assert.notNull(customerRequestDto, GenericConstant.MESSAGE_NOT_NULL);
        PersonEntity personEntity = new PersonEntity();

        if (customerRequestDto.getIdPerson() > 0) {
            Optional<PersonEntity> personEntityExists = this.personService.findById(customerRequestDto.getIdPerson());

            if (personEntityExists.isPresent()) {
                personEntity = personEntityExists.get();
            }
        }

        personEntity.setName(customerRequestDto.getName());
        personEntity.setState(customerRequestDto.getState());
        personEntity.setAge(customerRequestDto.getAge());
        personEntity.setGender(customerRequestDto.getGender());
        personEntity.setPhone(customerRequestDto.getPhone());
        personEntity.setAddress(customerRequestDto.getAddress());
        personEntity.setIdentification(customerRequestDto.getIdentification());
        personEntity = this.personService.save(personEntity);
        Assert.notNull(personEntity, GenericConstant.MESSAGE_NOT_PERSON_SAVED);

        CustomerEntity customerEntity = new CustomerEntity();

        if (customerRequestDto.getIdCustomer() > 0) {
            Optional<CustomerEntity> customerEntityExists = this.findById(customerRequestDto.getIdCustomer());

            if (customerEntityExists.isPresent()) {
                customerEntity = customerEntityExists.get();
            }
        }

        customerEntity.setIdPerson(personEntity.getId());
        customerEntity.setState(customerRequestDto.getState());
        customerEntity.setPassword(customerRequestDto.getPassword());
        customerEntity = this.save(customerEntity);
        Assert.notNull(customerEntity, GenericConstant.MESSAGE_NOT_CUSTOMER_SAVED);
    }
}
