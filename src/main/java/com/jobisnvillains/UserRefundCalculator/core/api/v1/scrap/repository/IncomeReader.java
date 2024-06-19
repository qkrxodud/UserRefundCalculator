package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.*;
import com.jobisnvillains.UserRefundCalculator.storage.CreditCardDeductionJpaEntity;
import com.jobisnvillains.UserRefundCalculator.storage.IncomeJpaEntity;
import com.jobisnvillains.UserRefundCalculator.storage.MonthlyDeductionAmountJapEntity;
import com.jobisnvillains.UserRefundCalculator.storage.PensionDeductionJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncomeReader {
    private final IncomeRepository incomeRepository;
    private final CreditCardDeductionRepository creditCardDeductionRepository;
    private final MonthlyDeductionAmountRepository monthlyDeductionAmountRepository;
    private final PensionDeductionRepository pensionDeductionRepository;

    public Income findByIncome(final Long scrapUserId) {
        IncomeJpaEntity incomeJpaEntity = incomeRepository.findByScrapUserJpaEntity_Id(scrapUserId)
                .orElse(new IncomeJpaEntity());

        // 국민연금
        List<PensionDeductionJpaEntity> pensionDeductionJpaEntities = pensionDeductionRepository.findAllByIncomeJpaEntity_Id(incomeJpaEntity.getId())
                .orElse(Collections.EMPTY_LIST);

        // 신용카드
        CreditCardDeductionJpaEntity creditCardDeductionJpaEntity = creditCardDeductionRepository.findByIncomeJpaEntity_Id(incomeJpaEntity.getId())
                .orElse(new CreditCardDeductionJpaEntity());

        List<MonthlyDeductionAmountJapEntity> monthlyDeductionAmountJapEntities = monthlyDeductionAmountRepository.findAllByCreditCardDeductionJpaEntity_Id(creditCardDeductionJpaEntity.getId())
                .orElse(Collections.EMPTY_LIST);

        return incomeJpaEntity.toModel(getPensionDeducations(pensionDeductionJpaEntities), getCreditCardDeduction(creditCardDeductionJpaEntity, monthlyDeductionAmountJapEntities));
    }

    private CreditCardDeduction getCreditCardDeduction(CreditCardDeductionJpaEntity creditCardDeductionJpaEntity, List<MonthlyDeductionAmountJapEntity> monthlyDeductionAmountJapEntities) {
        List<MonthlyDeductionAmount> monthlyDeductionAmountList = monthlyDeductionAmountJapEntities.stream()
                .map(monthlyDeductionAmountJapEntity -> monthlyDeductionAmountJapEntity.toModel())
                .collect(Collectors.toList());

        MonthlyDeductionAmounts monthlyDeductionAmounts = MonthlyDeductionAmounts.fromArrayList(monthlyDeductionAmountList);
        return creditCardDeductionJpaEntity.toModel(monthlyDeductionAmounts);
    }

    private PensionDeducations getPensionDeducations(List<PensionDeductionJpaEntity> pensionDeductionJpaEntities) {
        List<PensionDeduction> pensionDeductionList = pensionDeductionJpaEntities.stream()
                .map(pensionDeductionJpaEntity -> pensionDeductionJpaEntity.toModel())
                .collect(Collectors.toList());

        return new PensionDeducations(pensionDeductionList);
    }
}
