package com.example.demo1.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class VoucherCreationRequest {
    private String code;
    private String description;
    private BigDecimal discountAmount;
    private BigDecimal discountPercent;
    private Date validFrom;
    private Date validTo;
    private Integer usageLimit;
}
