package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CreditCardDeduction {
    private int year;
    private MonthlyDeductionAmounts monthlyDeductionAmounts;


    public CreditCardDeduction(Map<String, Object> deductions) {
        Map<String, Object> creditCard = (Map<String, Object>) deductions.get("신용카드소득공제");

        year = (Integer) creditCard.get("year");
        monthlyDeductionAmounts = new MonthlyDeductionAmounts((List<Map<String, String>>) creditCard.get("month"));
    }
}
