package com.InstiCab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDispute {
    private Integer status;
    private Long disputeId;
    private Long transactionId;
    private String description;
}
