package com.microservice.test.service;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.TransactionTypeRequestDto;
import com.microservice.test.entity.TransactionTypeEntity;
import com.microservice.test.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TransactionTypeService {
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public Iterable<TransactionTypeEntity> findAll() {
        return this.transactionTypeRepository.findAll();
    }

    public Optional<TransactionTypeEntity> findById(Integer id) {
        return this.transactionTypeRepository.findById(id);
    }

    public Optional<TransactionTypeEntity> findByTypeAndState(String type, Boolean state) {
        return this.transactionTypeRepository.findByTypeAndState(type, state);
    }

    @Transactional(rollbackOn = { Exception.class })
    public TransactionTypeEntity save (TransactionTypeRequestDto transactionTypeRequestDto) {
        Assert.notNull(transactionTypeRequestDto, GenericConstant.MESSAGE_NOT_NULL);
        TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();

        if (transactionTypeRequestDto.getId() > 0) {
            Optional<TransactionTypeEntity> transactionTypeEntityExists = this.findById(transactionTypeRequestDto.getId());

            if (transactionTypeEntityExists.isPresent()) {
                transactionTypeEntity = transactionTypeEntityExists.get();
            }
        }

        transactionTypeEntity.setState(transactionTypeRequestDto.getState());
        transactionTypeEntity.setType(transactionTypeRequestDto.getType());

        return this.transactionTypeRepository.save(transactionTypeEntity);
    }
}
