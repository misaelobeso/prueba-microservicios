package com.microservice.test.configuration;

import com.microservice.test.constant.GenericConstant;
import com.microservice.test.dto.MessageDto;
import com.microservice.test.dto.ResponseDto;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.validator.internal.engine.path.PathImpl;
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
import org.springframework.web.context.request.WebRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerConfig {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public final ResponseEntity<ResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> errors = new ArrayList<>();

        for (ObjectError error: exception.getBindingResult().getAllErrors()) {;
            messageDto.setElement(((FieldError) error).getField());
            messageDto.setMessage(error.getDefaultMessage());

            errors.add(messageDto);
            messageDto = new MessageDto();
        }

        responseDto.setErrors(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDto> handleConstraintViolation(
            ConstraintViolationException constraintViolationException,
            WebRequest request
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> errors = new ArrayList<>();

        for (ConstraintViolation constraintViolation: constraintViolationException.getConstraintViolations()) {
            PathImpl path = (PathImpl) constraintViolation.getPropertyPath();
            messageDto.setElement(path.getLeafNode().getName());
            messageDto.setMessage(constraintViolation.getMessage());
            errors.add(messageDto);
            messageDto = new MessageDto();
        }

        responseDto.setErrors(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseDto> handleAllExceptions(Exception ex, WebRequest request) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> errors = new ArrayList<>();
        messageDto.setMessage(ex.getLocalizedMessage());
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_SERVER);
        errors.add(messageDto);
        responseDto.setErrors(errors);

        return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<ResponseDto> handleUserNotFoundException(
            ObjectNotFoundException ex,
            WebRequest request
    ) {
        ResponseDto responseDto = new ResponseDto();
        MessageDto messageDto = new MessageDto();
        List<MessageDto> errors = new ArrayList<>();
        messageDto.setMessage(ex.getLocalizedMessage());
        messageDto.setElement(GenericConstant.VALIDATE_ELEMENT_NOT_FOUND);
        errors.add(messageDto);
        responseDto.setErrors(errors);

        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }
}
