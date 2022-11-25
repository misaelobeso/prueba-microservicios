package com.microservice.test.service;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.AccountRequestDto;
import com.microservice.test.entity.AccountEntity;
import com.microservice.test.entity.AccountTypeEntity;
import com.microservice.test.entity.CustomerEntity;
import com.microservice.test.repository.AccountRepository;
import com.microservice.test.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    @Transactional(rollbackOn = { Exception.class })
    public AccountEntity save(Integer id, AccountRequestDto accountRequestDto) {
        AccountEntity accountEntity = new AccountEntity();

        if (id > GenericConstant.DEFAULT_INTEGER) {
            Optional<AccountEntity> accountEntityExists = this.accountRepository.findById(id);
            Assert.isTrue(accountEntityExists.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_ACCOUNT);

            accountEntity = accountEntityExists.get();
        }

        Optional<AccountTypeEntity> accountTypeEntity = this.accountTypeService.findByName(accountRequestDto.getAccountType());
        Assert.isTrue(accountTypeEntity.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_ACCOUNTTYPE);

        Optional<CustomerEntity> customerEntity = this.customerService.findById(accountRequestDto.getIdCustomer());
        Assert.isTrue(customerEntity.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_CUSTOMER);

        accountEntity.setIdAccountType(accountTypeEntity.get().getId());
        accountEntity.setIdCustomer(customerEntity.get().getId());
        accountEntity.setInitialBalance(accountRequestDto.getInitialBalance());
        accountEntity.setCurrentBalance(accountRequestDto.getCurrentBalance());
        accountEntity.setState(accountRequestDto.getState());

        accountEntity = this.save(accountEntity);
        Assert.notNull(accountEntity, GenericConstant.MESSAGE_NOT_ACCOUNT_SAVED);

        return accountEntity;
    }

    public Boolean hasBalanceAvailable(Integer id) {
         Optional<AccountEntity> accountEntity = this.findById(id);
         return (accountEntity.isPresent() && accountEntity.get().getCurrentBalance() > 0);
    }

    @Transactional(rollbackOn = { Exception.class })
    public void deleteByState (Integer id) {
        Optional<AccountEntity> accountEntity = this.findById(id);
        Assert.isTrue(accountEntity.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_ACCOUNT);

        accountEntity.get().setState(GenericConstant.INACTIVE_STATE);
        AccountEntity accountEntitySaved = this.save(accountEntity.get());
        Assert.notNull(accountEntitySaved, GenericConstant.MESSAGE_NOT_CUSTOMER_SAVED);
    }
}
