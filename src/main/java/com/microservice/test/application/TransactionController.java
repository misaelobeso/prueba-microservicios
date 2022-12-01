package com.microservice.test.application;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.MessageDto;
import com.microservice.test.dto.ResponseDto;
import com.microservice.test.dto.TransactionRequestDto;
import com.microservice.test.entity.TransactionEntity;
import com.microservice.test.service.TransactionService;
import com.microservice.test.validator.IsDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> get() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(this.transactionService.findAll());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
    public ResponseEntity<ResponseDto> deleteByState(
            @NotNull(message = GenericConstant.MESSAGE_TRANSACTION_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_TRANSACTION_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_TRANSACTION);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.transactionService.deleteByState(id);
            messageDto.setMessage(GenericConstant.MESSAGE_TRANSACTION_ELIMINATED);
            messageDtos.add(messageDto);
            responseDto.setMessages(messageDtos);
        } catch (Exception e) {
            messageDto.setMessage(e.getMessage());
            messageDtos.add(messageDto);
            responseDto.setErrors(messageDtos);
            httpStatus = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @GetMapping("/report/{idCustomer}")
    public ResponseEntity<ResponseDto> getReport(
            @NotNull(message = GenericConstant.MESSAGE_CUSTOMER_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_CUSTOMER_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer idCustomer,
            @NotNull(message = GenericConstant.MESSAGE_REQUIRED_DATE)
            @IsDate(value = "yyyy/MM/dd", message = GenericConstant.MESSAGE_INVALID_DATE)
            @RequestParam String startDate,
            @NotNull(message = GenericConstant.MESSAGE_REQUIRED_DATE)
            @IsDate(value = "yyyy/MM/dd", message = GenericConstant.MESSAGE_INVALID_DATE)
            @RequestParam String endDate
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_REPORT);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            responseDto.setData(this.transactionService.report(idCustomer, startDate, endDate));
            messageDto.setMessage(GenericConstant.MESSAGE_REPORT_GENERATED);
            messageDtos.add(messageDto);
            responseDto.setMessages(messageDtos);
        } catch (Exception e) {
            messageDto.setMessage(e.getMessage());
            messageDtos.add(messageDto);
            responseDto.setErrors(messageDtos);
            httpStatus = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }
}
