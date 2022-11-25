package com.microservice.test.application;

import com.microservice.test.dto.CustomerRequestDto;
import com.microservice.test.entity.CustomerEntity;
import com.microservice.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/get/{id}")
    public Optional<CustomerEntity> getById(@PathVariable Integer id) {
        return this.customerService.findById(id);
    }

    @GetMapping("/get")
    public Iterable<CustomerEntity> get() {
        return this.customerService.findAll();
    }

    @PostMapping("/save")
    public void save(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        this.customerService.saveCustomerAndPerson(customerRequestDto);
    }
}
