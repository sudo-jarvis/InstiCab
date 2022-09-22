package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private Long couponId;
    @NotEmpty
    private float couponDiscount;
    @NotEmpty
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date couponValidity;
    @NotEmpty
    private int maxDiscount;
}