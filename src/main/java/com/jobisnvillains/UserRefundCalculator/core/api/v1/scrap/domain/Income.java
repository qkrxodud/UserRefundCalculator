package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.service.TaxService;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.EmptyIncomeDataStateException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Getter
public class Income {
    private static final Long IS_NOT_REGISTER_INCOME = -1L;
    private static final String EMPTY_DATA = "income is empty.";
    private static final String RESPONSE_DATA_EMPTY = "income is empty.";
    private static final String STRING_TO_NUMBER_FORMAT = "string to number format Ex.";
    private static final String NAME = "이름";
    private static final String COMPREHENSIVE_INCOME = "종합소득금액";
    private static final String DEDUCTION = "소득공제";
    private static final String TAX_CREDIT = "세액공제";

    private Long id;
    private String name;
    private Long comprehensiveIncomeAmount;
    private PensionDeducations pensionDeductions;
    private CreditCardDeduction creditCardDeduction;
    private Long taxCredit;

    @Builder
    public Income(Long id, String name, Long comprehensiveIncomeAmount, PensionDeducations pensionDeductions, CreditCardDeduction creditCardDeduction, Long taxCredit) {
        this.id = Optional.ofNullable(id)
                .orElse(IS_NOT_REGISTER_INCOME);
        this.name = name;
        this.comprehensiveIncomeAmount = comprehensiveIncomeAmount;
        this.pensionDeductions = pensionDeductions;
        this.creditCardDeduction = creditCardDeduction;
        this.taxCredit = taxCredit;
    }

    public void checkIncome() {
        if (id == IS_NOT_REGISTER_INCOME) {
            throw new EmptyIncomeDataStateException(EMPTY_DATA);
        }
    }

    public Income(Map<String, Object> responseData) {
        if (CollectionUtils.isEmpty(responseData)) {
            throw new IllegalStateException(RESPONSE_DATA_EMPTY);
        }
        Map<String, Object> data = (Map<String, Object>) responseData.get("data");
        name = (String) data.get(NAME);
        comprehensiveIncomeAmount = getComprehensiveIncomeAmount(data.get(COMPREHENSIVE_INCOME));

        Map<String, Object> deductions = (Map<String, Object>) data.get(DEDUCTION);
        pensionDeductions = new PensionDeducations(deductions);
        creditCardDeduction = new CreditCardDeduction(deductions);

        taxCredit = getTexCredit(deductions.get(TAX_CREDIT));
    }


    private Long getComprehensiveIncomeAmount(Object comprehensiveIncomeAmount) {
        if (comprehensiveIncomeAmount instanceof Long) {
            return (Long)comprehensiveIncomeAmount;
        }

        if (comprehensiveIncomeAmount instanceof Integer) {
            return Long.valueOf((Integer) comprehensiveIncomeAmount);
        }

        if (comprehensiveIncomeAmount instanceof String) {
            return Long.parseLong((String)comprehensiveIncomeAmount);
        }
        return 0L;
    }

    private Long getTexCredit (Object taxCredit) {
        if (taxCredit instanceof String) {
            return convertStingMoneyToLongMoney((String) taxCredit);
        }
        if (taxCredit instanceof Integer) {
            return Long.valueOf((Integer) taxCredit);
        }
        return 0L;
    }

    private Long convertStingMoneyToLongMoney(String value) {
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.KOREA);
            Number number = format.parse(value);
            return number.longValue();
        } catch (ParseException e) {
            throw new NumberFormatException(STRING_TO_NUMBER_FORMAT + value);
        }
    }

    public String calculatedTaxAmount(TaxService taxService) {
        Double deduction = creditCardDeduction.getCreditCardDeduction() + pensionDeductions.getPensionDeduction();
        return taxService.calculateTax(comprehensiveIncomeAmount, deduction, taxCredit);
    }
}
