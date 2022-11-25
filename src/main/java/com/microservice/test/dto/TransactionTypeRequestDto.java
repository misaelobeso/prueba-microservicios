package com.microservice.test.dto;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;

@Data
public class TransactionTypeRequestDto {
    private Integer id;

    private String type;

    private Boolean state = GenericConstant.ACTIVE_STATE;
}
