package com.microservice.test.application;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.ReportDto;
import com.microservice.test.dto.TransactionRequestDto;
import com.microservice.test.entity.TransactionEntity;
import com.microservice.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/get")
    public Iterable<TransactionEntity> get() {
        return this.transactionService.findAll();
    }

    @GetMapping("/get/{state}")
    public Iterable<TransactionEntity> get(@PathVariable Boolean state) {
        return this.transactionService.findByState(state);
    }

    @PostMapping("/save")
    public TransactionEntity save(@RequestBody TransactionRequestDto transactionRequestDto) {
        return this.transactionService.save(GenericConstant.DEFAULT_INTEGER, transactionRequestDto);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Integer id, @RequestBody TransactionRequestDto transactionRequestDto) {
        this.transactionService.save(id, transactionRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable Integer id) {
        this.transactionService.delete(id);
    }

    @GetMapping("/report/{id}")
    public List<ReportDto> getReport(
            @PathVariable Integer idCustomer,
            @RequestParam Date startDate,
            @RequestParam Date endDate
    ) {
        return this.transactionService.report(idCustomer, startDate, endDate);
    }
}
