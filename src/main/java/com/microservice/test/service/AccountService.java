package com.microservice.test.service;

import com.microservice.test.entity.AccountEntity;
import com.microservice.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private CustomerService customerService;

    public Iterable<AccountEntity> findAll(){
        return this.accountRepository.findAll();
    }

    public Optional<AccountEntity> findById (Integer id) {
        return this.accountRepository.findById(id);
    }

    public List<AccountEntity> findByIdCustomer(Integer idCustomer) {
        return this.accountRepository.findByIdCustomer(idCustomer);
    }

    @Transactional(rollbackOn = { Exception.class })
    public AccountEntity save(AccountEntity accountEntity) {
        return this.accountRepository.save(accountEntity);
    }

    public Boolean hasBalanceAvailable(Integer id) {
         Optional<AccountEntity> accountEntity = this.findById(id);
         return (accountEntity.isPresent() && accountEntity.get().getCurrentBalance() > 0);
    }
}
