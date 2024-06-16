package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller.info;

public class PensionDeduction {
    private String month;
    private String amount;

    public PensionDeduction(String month, String amount) {
        this.month = month;
        this.amount = amount;
    }
}
