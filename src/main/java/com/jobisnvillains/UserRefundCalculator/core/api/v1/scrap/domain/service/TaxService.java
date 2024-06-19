package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaxService {
    public String calculateTax(final Long income, final Double deduction, final Long taxCredit) {
        // 과세표준
        Double taxBase = income - deduction; // 과세표준 = 종합소득금액 - 소득공제
        TaxBracket bracket = TaxBracket.getBracket(taxBase);

        double calculatedTaxAmount;
        if (bracket == TaxBracket.BRACKET_1) {
            calculatedTaxAmount = taxBase * bracket.getRate();
        } else {
            calculatedTaxAmount = bracket.getFixedAmount() + (taxBase - bracket.getLowerBound() + 1) * bracket.getRate();
        }

        double determinedTaxAmount = calculatedTaxAmount - taxCredit;
        return String.format("%,d", Math.round(determinedTaxAmount));
    }
}
