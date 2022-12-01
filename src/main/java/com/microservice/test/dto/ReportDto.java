package com.microservice.test.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReportDto {
    private Date date;

    private String customerName;

    private Integer account;

    private String accountType = "";

    private Integer initialBalance = 0;

    private Boolean state;

    private Integer idTransaction;

    private Integer currentBalance = 0;
}
