package com.microservice.test.application;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.AccountRequestDto;
import com.microservice.test.dto.MessageDto;
import com.microservice.test.dto.ResponseDto;
import com.microservice.test.entity.AccountEntity;
import com.microservice.test.service.AccountService;
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
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> get() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(this.accountService.findAll());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getById(
            @NotNull(message = GenericConstant.MESSAGE_ACCOUNT_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_ACCOUNT_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_ACCOUNT);
        Optional<AccountEntity> accountEntity = this.accountService.findById(id);
        messageDto.setMessage(GenericConstant.MESSAGE_NOT_EXISTS_ACCOUNT);
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        if (accountEntity.isPresent()) {
            httpStatus = HttpStatus.OK;
            messageDto.setMessage(GenericConstant.MESSAGE_EXISTS_ACCOUNT);
            responseDto.setData(accountEntity.get());
            messageDtos.add(messageDto);
            responseDto.setMessages(messageDtos);
        } else {
            messageDtos.add(messageDto);
            responseDto.setErrors(messageDtos);
        }

        return new ResponseEntity<>(responseDto, httpStatus);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto> save(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_ACCOUNT);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.accountService.save(GenericConstant.DEFAULT_INTEGER, accountRequestDto);
            messageDto.setMessage(GenericConstant.MESSAGE_ACCOUNT_SAVED);
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

    @PutMapping("/save/{id}")
    public ResponseEntity<ResponseDto> save(
            @NotNull(message = GenericConstant.MESSAGE_ACCOUNT_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_ACCOUNT_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id,
            @Valid @RequestBody AccountRequestDto accountRequestDto
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_ACCOUNT);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.accountService.save(id, accountRequestDto);
            messageDto.setMessage(GenericConstant.MESSAGE_ACCOUNT_SAVED);
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
            @NotNull(message = GenericConstant.MESSAGE_ACCOUNT_REQUIRED)
            @Min(message = GenericConstant.MESSAGE_ACCOUNT_MIN_VALUE, value = GenericConstant.DEFAULT_INTEGER)
            @PathVariable Integer id
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> messageDtos = new ArrayList<>();
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_ACCOUNT);
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            this.accountService.deleteByState(id);
            messageDto.setMessage(GenericConstant.MESSAGE_ACCOUNT_ELIMINATED);
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
