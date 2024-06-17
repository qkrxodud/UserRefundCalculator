package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.storage.IncomeJpaEntity;
import com.jobisnvillains.UserRefundCalculator.storage.PensionDeductionJpaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PensionDeducations {
    List<PensionDeduction> pensionDeductions;

    public PensionDeducations(Map<String, Object> deductions) {
        List<Map<String, Object>> pensions = (List<Map<String, Object>>) deductions.get("국민연금");
        pensionDeductions = pensions.stream()
                .map(p -> new PensionDeduction((String) p.get("월"), (String) p.get("공제액")))
                .collect(Collectors.toList());
    }

    public List<PensionDeductionJpaEntity> getPensionDeductionJpaEntities(final IncomeJpaEntity incomeJpaEntity) {
        return pensionDeductions.stream()
                .map(pensionDeduction -> PensionDeductionJpaEntity.fromModel(pensionDeduction, incomeJpaEntity))
                .collect(Collectors.toList());
    }
}
