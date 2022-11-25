package com.microservice.test.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseDto {
    private List<MessageDto> messages;

    private List<MessageDto> errors;

    private Object data;
}
