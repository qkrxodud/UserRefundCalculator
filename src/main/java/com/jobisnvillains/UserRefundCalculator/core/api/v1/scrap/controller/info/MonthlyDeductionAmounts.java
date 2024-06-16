package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller.info;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyDeductionAmounts {
    private List<MonthlyDeductionAmount> monthlyDeductionAmountList;

    public MonthlyDeductionAmounts(List<Map<String, String>> monthlyDeductionAmounts) {
        monthlyDeductionAmountList = monthlyDeductionAmounts.stream()
                .flatMap(m -> m.entrySet().stream()
                        .map(entry -> new MonthlyDeductionAmount(entry.getKey(), entry.getValue())))
                .collect(Collectors.toList());
    }
}
