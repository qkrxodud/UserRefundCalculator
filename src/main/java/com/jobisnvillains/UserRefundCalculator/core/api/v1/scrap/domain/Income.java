package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

@Getter
public class Income {
    private String name;
    private Long comprehensiveIncomeAmount;
    private PensionDeducations pensionDeductions;
    private CreditCardDeduction creditCardDeduction;
    private Long taxCredit;


    public Income(Map<String, Object> responseData) {
        if (CollectionUtils.isEmpty(responseData)) {
            throw new IllegalStateException("Income 값이 비워 있습니다.");
        }
        Map<String, Object> data = (Map<String, Object>) responseData.get("data");
        name = (String) data.get("이름");
        comprehensiveIncomeAmount = getComprehensiveIncomeAmount(data.get("종합소득금액"));

        Map<String, Object> deductions = (Map<String, Object>) data.get("소득공제");
        pensionDeductions = new PensionDeducations(deductions);
        creditCardDeduction = new CreditCardDeduction(deductions);

        taxCredit = getTexCredit(deductions.get("세액공제"));
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
            throw new NumberFormatException("Invalid number format: " + value);
        }
    }
}
