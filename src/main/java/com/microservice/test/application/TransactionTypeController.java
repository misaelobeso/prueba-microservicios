package com.microservice.test.application;

import com.microservice.test.dto.ResponseDto;
import com.microservice.test.service.PersonService;
import com.microservice.test.service.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction/type", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionTypeController {
    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> get() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(this.transactionTypeService.findAll());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
