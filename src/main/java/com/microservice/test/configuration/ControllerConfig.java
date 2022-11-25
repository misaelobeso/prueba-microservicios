package com.microservice.test.configuration;

import com.microservice.test.dto.MessageDto;
import com.microservice.test.dto.ResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerConfig {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> errors = new ArrayList<>();

        for (ObjectError error: exception.getBindingResult().getAllErrors()) {
            messageDto.setElement(((FieldError) error).getField());
            messageDto.setMessage(error.getDefaultMessage());

            errors.add(messageDto);
            messageDto = new MessageDto();
        }

        responseDto.setErrors(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
