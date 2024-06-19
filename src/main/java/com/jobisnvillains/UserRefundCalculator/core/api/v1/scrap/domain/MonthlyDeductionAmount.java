package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MonthlyDeductionAmount {
    private String month;
    private String deduction;

    @Builder
    public MonthlyDeductionAmount(String month, String deduction) {
        this.month = month;
        this.deduction = deduction;
    }
}
