package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.CreditCardDeductionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardDeductionRepository extends JpaRepository<CreditCardDeductionJpaEntity, Long> {
    Optional<CreditCardDeductionJpaEntity> findByIncomeJpaEntity_Id(final Long incomeId);
}
