package com.microservice.test.application;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.CustomerRequestDto;
import com.microservice.test.dto.MessageDto;
import com.microservice.test.dto.ResponseDto;
import com.microservice.test.entity.CustomerEntity;
import com.microservice.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getById(
            @NotNull(message = GenericConstant.MESSAGE_CUSTOMER_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_CUSTOMER_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_CUSTOMER);
        Optional<CustomerEntity> customerEntity = this.customerService.findById(id);
        messageDto.setMessage(GenericConstant.MESSAGE_NOT_EXISTS_CUSTOMER);
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        if (customerEntity.isPresent()) {
            httpStatus = HttpStatus.OK;
            messageDto.setMessage(GenericConstant.MESSAGE_EXISTS_CUSTOMER);
            responseDto.setData(customerEntity.get());
            messageDtos.add(messageDto);
            responseDto.setMessages(messageDtos);
        } else {
            messageDtos.add(messageDto);
            responseDto.setErrors(messageDtos);
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> get() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(this.customerService.findAll());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_CUSTOMER);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.customerService.saveCustomerAndPerson(customerRequestDto);
            messageDto.setMessage(GenericConstant.MESSAGE_CUSTOMER_SAVED);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteByState (
            @NotNull(message = GenericConstant.MESSAGE_CUSTOMER_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_CUSTOMER_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_CUSTOMER);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.customerService.deleteByState(id);
            messageDto.setMessage(GenericConstant.MESSAGE_CUSTOMER_ELIMINATED);
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
