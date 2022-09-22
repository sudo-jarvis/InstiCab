package com.InstiCab.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EarningsHistoryDto {
    private Long earningId;
    @NotEmpty
    private float cost;
    @NotEmpty
    private float distanceTravelled;
}