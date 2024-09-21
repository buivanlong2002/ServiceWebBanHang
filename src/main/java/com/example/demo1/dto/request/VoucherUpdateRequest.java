package com.example.demo1.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
public class VoucherUpdateRequest {
    private String code;
    private String description;
    private BigDecimal discountAmount;
    private BigDecimal discountPercent;
    private Date validFrom;
    private Date validTo;
    private Integer usageLimit;
}
