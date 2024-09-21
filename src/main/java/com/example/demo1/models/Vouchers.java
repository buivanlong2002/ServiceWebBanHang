package com.example.demo1.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Vouchers {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "STT_COMMON")
        @SequenceGenerator(name="STT_COMMON",sequenceName = "STT_COMMON",allocationSize=1)
        @Column(name = "voucher_id")
        private Integer id;

        @Column(name = "code", nullable = false, unique = true)
        private String code;

        @Column(name = "description")
        private String description;

        @Column(name = "discount_amount", precision = 10, scale = 2)
        private BigDecimal discountAmount;

        @Column(name = "discount_percent", precision = 5, scale = 2)
        private BigDecimal discountPercent;


        @Column(name = "valid_from")
        private Date validFrom;

        @Column(name = "valid_to")
        private Date validTo;

        @Column(name = "usage_limit")
        private Integer usageLimit;
//        public BigDecimal getDiscountValue() {
//                if (discountAmount != null) {
//                        return discountAmount;
//                } else if (discountPercent != null) {
//                        return discountPercent;
//                } else {
//                        return BigDecimal.ZERO; // or handle the case when both are null
//                }
//        }
}
