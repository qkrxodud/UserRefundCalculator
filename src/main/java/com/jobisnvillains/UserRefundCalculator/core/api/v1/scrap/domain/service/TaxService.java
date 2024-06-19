package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaxService {
    public String calculateTax(final Long income, final Double deduction, final Long taxCredit) {
        Double taxBase = income - deduction;
        Tax bracket = Tax.getBracket(taxBase);

        double calculatedTaxAmount;
        if (bracket == Tax.BRACKET_1) {
            calculatedTaxAmount = taxBase * bracket.getRate();
        } else {
            calculatedTaxAmount = bracket.getFixedAmount() + (taxBase - bracket.getLowerBound()) * bracket.getRate();
        }

        double determinedTaxAmount = calculatedTaxAmount - taxCredit;
        return String.format("%,d", Math.round(determinedTaxAmount));
    }
}
