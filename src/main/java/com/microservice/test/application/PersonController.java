package com.microservice.test.application;

import com.microservice.test.dto.ResponseDto;
import com.microservice.test.entity.PersonEntity;
import com.microservice.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDto> get() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(this.personService.findAll());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
