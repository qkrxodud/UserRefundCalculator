package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.*;
import com.jobisnvillains.UserRefundCalculator.storage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncomeAppender {
    private final IncomeRepository incomeRepository;
    private final CreditCardDeductionRepository creditCardDeductionRepository;
    private final MonthlyDeductionAmountRepository monthlyDeductionAmountRepository;
    private final PensionDeductionRepository pensionDeductionRepository;

    @Transactional
    public void saveIncome(ScrapUser scrapUser) {
        // 유저정보
        ScrapUserJpaEntity scrapUserJpaEntity = ScrapUserJpaEntity.fromModel(scrapUser);

        // 소득
        IncomeJpaEntity incomeJpaEntity = IncomeJpaEntity.fromModel(scrapUser.getIncome(), scrapUserJpaEntity);
        incomeRepository.save(incomeJpaEntity);

        // 신용카드 소득 공제
        CreditCardDeduction creditCardDeduction = scrapUser.getIncome()
                .getCreditCardDeduction();
        CreditCardDeductionJpaEntity creditCardDeductionJpaEntity = CreditCardDeductionJpaEntity.fromModel(creditCardDeduction, incomeJpaEntity);
        creditCardDeductionRepository.save(creditCardDeductionJpaEntity);

        // 신용카드 소득 공제에 따른 월별 금액
        List<MonthlyDeductionAmountJapEntity> monthlyDeductionAmountJpaEntity = creditCardDeduction.getMonthlyDeductionAmounts()
                .getMonthlyDeductionAmountJpaEntities(creditCardDeductionJpaEntity);
        monthlyDeductionAmountRepository.saveAll(monthlyDeductionAmountJpaEntity);

        // 연금 공제
        PensionDeducations pensionDeductions = scrapUser.getIncome()
                .getPensionDeductions();
        List<PensionDeductionJpaEntity> pensionDeductionJpaEntities = pensionDeductions.getPensionDeductionJpaEntities(incomeJpaEntity);
        pensionDeductionRepository.saveAll(pensionDeductionJpaEntities);
    }
}
