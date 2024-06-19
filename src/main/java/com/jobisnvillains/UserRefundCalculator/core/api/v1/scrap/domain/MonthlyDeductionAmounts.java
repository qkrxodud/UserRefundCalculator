package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.storage.CreditCardDeductionJpaEntity;
import com.jobisnvillains.UserRefundCalculator.storage.MonthlyDeductionAmountJapEntity;
import lombok.Getter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class MonthlyDeductionAmounts {
    private static final String PARSE_AMOUNT_FAIL = "Failed to parse amount";
    private List<MonthlyDeductionAmount> monthlyDeductionAmountList;

    private MonthlyDeductionAmounts(List<MonthlyDeductionAmount> monthlyDeductionAmountList) {
        this.monthlyDeductionAmountList = monthlyDeductionAmountList;
    }

    public static MonthlyDeductionAmounts fromMapList(List<Map<String, String>> monthlyDeductionAmounts) {
        List<MonthlyDeductionAmount> list = monthlyDeductionAmounts.stream()
                .flatMap(m -> m.entrySet().stream()
                        .map(entry -> new MonthlyDeductionAmount(entry.getKey(), entry.getValue())))
                .collect(Collectors.toList());
        return new MonthlyDeductionAmounts(list);
    }

    public static MonthlyDeductionAmounts fromArrayList(List<MonthlyDeductionAmount> monthlyDeductionAmountList) {
        return new MonthlyDeductionAmounts(monthlyDeductionAmountList);
    }

    public List<MonthlyDeductionAmountJapEntity> getMonthlyDeductionAmountJpaEntities(final CreditCardDeductionJpaEntity creditCardDeductionJpaEntity) {
        return monthlyDeductionAmountList.stream()
                .map(monthlyDeductionAmount -> MonthlyDeductionAmountJapEntity.fromModel(monthlyDeductionAmount, creditCardDeductionJpaEntity))
                .collect(Collectors.toList());
    }

    public Double getMonthlyDeductionAmounts() {
        return monthlyDeductionAmountList.stream()
                .mapToDouble(monthlyDeductionAmount -> parseAmount(monthlyDeductionAmount.getDeduction()))
                .sum();
    }

    private double parseAmount(String amount) {
        try {
            NumberFormat format = NumberFormat.getNumberInstance(Locale.KOREA);
            return format.parse(amount).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(PARSE_AMOUNT_FAIL + amount, e);
        }
    }
}
