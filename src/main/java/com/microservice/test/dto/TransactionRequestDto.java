package com.microservice.test.dto;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private Integer idAccount;

    private Integer value = GenericConstant.DEFAULT_INTEGER;

    private Integer currentBalance = GenericConstant.DEFAULT_INTEGER;

    private Boolean state = GenericConstant.ACTIVE_STATE;
}
