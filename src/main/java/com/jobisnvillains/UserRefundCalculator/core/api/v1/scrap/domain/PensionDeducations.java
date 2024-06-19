package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.storage.IncomeJpaEntity;
import com.jobisnvillains.UserRefundCalculator.storage.PensionDeductionJpaEntity;
import lombok.Getter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PensionDeducations {
    private static final String PARSE_AMOUNT_FAIL = "Failed to parse amount";
    private static final String PENSION_DEDUCTION = "국민연금";
    private static final String MONTH = "월";
    private static final String DEDUCTION = "공제액";

    List<PensionDeduction> pensionDeductions;

    public PensionDeducations(Map<String, Object> deductions) {
        List<Map<String, Object>> pensions = (List<Map<String, Object>>) deductions.get(PENSION_DEDUCTION);
        pensionDeductions = pensions.stream()
                .map(p -> new PensionDeduction((String) p.get(MONTH), (String) p.get(DEDUCTION)))
                .collect(Collectors.toList());
    }

    public PensionDeducations(List<PensionDeduction> pensionDeductions) {
        this.pensionDeductions = pensionDeductions;
    }

    public List<PensionDeductionJpaEntity> getPensionDeductionJpaEntities(final IncomeJpaEntity incomeJpaEntity) {
        return pensionDeductions.stream()
                .map(pensionDeduction -> PensionDeductionJpaEntity.fromModel(pensionDeduction, incomeJpaEntity))
                .collect(Collectors.toList());
    }

    public Double getPensionDeduction() {
        return pensionDeductions.stream()
                .mapToDouble(pensionDeduction -> parseAmount(pensionDeduction.getAmount()))
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
