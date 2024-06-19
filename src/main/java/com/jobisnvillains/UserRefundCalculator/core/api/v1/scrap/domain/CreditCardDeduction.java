package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CreditCardDeduction {
    private static final String CREDIT_CARD_DEDUCTION = "신용카드소득공제";
    private static final String MONTH = "month";

    private int year;
    private MonthlyDeductionAmounts monthlyDeductionAmounts;

    public CreditCardDeduction(Map<String, Object> deductions) {
        Map<String, Object> creditCard = (Map<String, Object>) deductions.get(CREDIT_CARD_DEDUCTION);

        year = (Integer) creditCard.get("year");
        monthlyDeductionAmounts = MonthlyDeductionAmounts.fromMapList((List<Map<String, String>>) creditCard.get(MONTH));
    }

    @Builder
    public CreditCardDeduction(int year, final MonthlyDeductionAmounts monthlyDeductionAmounts) {
        this.year = year;
        this.monthlyDeductionAmounts = monthlyDeductionAmounts;
    }

    public Double getCreditCardDeduction() {
        return monthlyDeductionAmounts.getMonthlyDeductionAmounts();
    }
}
