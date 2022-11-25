package com.microservice.test.dto;

import com.microservice.test.constant.GenericConstant;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class AccountRequestDto {
    @NotNull(message = GenericConstant.MESSAGE_ACCOUNTYPE_REQUIRED)
    private String accountType;

    @NotNull(message = GenericConstant.MESSAGE_CUSTOMER_REQUIRED)
    private Integer idCustomer;

    @NotNull(message = GenericConstant.MESSAGE_INITIAL_BALANCE_REQUIRED)
    private Integer initialBalance = GenericConstant.DEFAULT_INTEGER;

    @NotNull(message = GenericConstant.MESSAGE_CURRENT_BALANCE_REQUIRED)
    private Integer currentBalance = GenericConstant.DEFAULT_INTEGER;

    @NotNull(message = GenericConstant.MESSAGE_ESTATE_REQUIRED)
    private Boolean state = GenericConstant.ACTIVE_STATE;
}
