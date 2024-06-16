package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller.info;

public class MonthlyDeductionAmount {
    private String month;
    private String deduction;

    public MonthlyDeductionAmount(String month, String deduction) {
        this.month = month;
        this.deduction = deduction;
    }
}
