package com.microservice.test.service;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.ReportDto;
import com.microservice.test.dto.TransactionRequestDto;
import com.microservice.test.entity.AccountEntity;
import com.microservice.test.entity.AccountTypeEntity;
import com.microservice.test.entity.TransactionEntity;
import com.microservice.test.entity.TransactionTypeEntity;
import com.microservice.test.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private CustomerService customerService;

    public Optional<TransactionEntity> findById(Integer id) {
        return this.transactionRepository.findById(id);
    }

    public List<TransactionEntity> findByIdAccountAndCreatedAtBetween(Integer idAccount,Date start, Date end) {
        return this.transactionRepository.findByIdAccountAndCreatedAtBetween(idAccount, start, end);
    }

    public Iterable<TransactionEntity> findAll() {
        return this.transactionRepository.findAll();
    }

    public Iterable<TransactionEntity> findByState(Boolean state) {
        return this.transactionRepository.findByState(state);
    }

    @Transactional(rollbackOn = { Exception.class })
    public TransactionEntity save (Integer id, TransactionRequestDto transactionRequestDto) {
        Assert.notNull(transactionRequestDto, GenericConstant.MESSAGE_NOT_NULL);
        TransactionEntity transactionEntity = new TransactionEntity();

        if (id > GenericConstant.DEFAULT_INTEGER) {
            Optional<TransactionEntity> transactionExists = this.findById(id);
            Assert.isTrue(transactionExists.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_TRANSACTION);

            transactionEntity = transactionExists.get();
        }

        String transactionTypeName
                = transactionRequestDto.getValue() < GenericConstant.DEFAULT_INTEGER
                ? GenericConstant.TRASACTION_TYPE_DEBIT : GenericConstant.TRASACTION_TYPE_CREDIT;

        Optional<TransactionTypeEntity> transactionTypeEntity =
                this.transactionTypeService.findByTypeAndState(transactionTypeName, GenericConstant.ACTIVE_STATE);
        Assert.isTrue(transactionTypeEntity.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_TRANSACTION_TYPE);

        Optional<AccountEntity> accountEntity =
                this.accountService.findById(transactionRequestDto.getIdAccount());
        Assert.isTrue(accountEntity.isPresent(), GenericConstant.MESSAGE_NOT_EXISTS_ACCOUNT);

        Integer currentBalance = transactionRequestDto.getCurrentBalance();

        if (id == GenericConstant.DEFAULT_INTEGER) {
            currentBalance = accountEntity.get().getCurrentBalance();
            currentBalance += transactionRequestDto.getValue();
        }

        transactionEntity.setState(transactionRequestDto.getState());
        transactionEntity.setValue(transactionRequestDto.getValue());
        transactionEntity.setIdAccount(transactionRequestDto.getIdAccount());
        transactionEntity.setCurrentBalance(currentBalance);
        transactionEntity.setIdTransactionType(transactionTypeEntity.get().getId());

        return this.transactionRepository.save(transactionEntity);
    }

    @Transactional(rollbackOn = { Exception.class })
    public void delete(Integer id) {
        Assert.notNull(id, GenericConstant.MESSAGE_NOT_NULL);

        this.transactionRepository.deleteById(id);
    }

    public List<ReportDto> report(Integer idCustomer, Date start, Date end) {
        List<AccountEntity> accountEntities = this.accountService.findByIdCustomer(idCustomer);
        List<ReportDto> data = new ArrayList<>();
        ReportDto reportDto = new ReportDto();
        Optional<AccountTypeEntity> accountTypeEntity;
        List<TransactionEntity> transactionEntities = new ArrayList<>();

        for (AccountEntity accountEntity: accountEntities) {
            reportDto.setAccount(accountEntity.getId());
            accountTypeEntity = this.accountTypeService.findById(accountEntity.getIdAccountType());

            if (accountTypeEntity.isPresent()) {
                reportDto.setAccountType(accountTypeEntity.get().getName());
            }

            reportDto.setCustomerName(this.customerService.findCustomerNameById(accountEntity.getIdCustomer()));
            transactionEntities = this.findByIdAccountAndCreatedAtBetween(accountEntity.getId(), start, end);

            if (transactionEntities.size() > 0) {
                for (TransactionEntity transactionEntity : transactionEntities) {
                    reportDto.setIdTransaction(transactionEntity.getId());
                    reportDto.setState(transactionEntity.getState());
                    reportDto.setInitialBalance(accountEntity.getInitialBalance());
                    reportDto.setCurrentBalance(transactionEntity.getCurrentBalance());
                    reportDto.setDate(transactionEntity.getCreatedAt());
                    data.add(reportDto);
                }
            }
            reportDto = new ReportDto();
        }

        return data;
    }
}
