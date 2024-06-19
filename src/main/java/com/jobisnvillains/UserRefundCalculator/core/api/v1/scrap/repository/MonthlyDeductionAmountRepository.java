package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.MonthlyDeductionAmountJapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MonthlyDeductionAmountRepository extends JpaRepository<MonthlyDeductionAmountJapEntity, Long> {
    Optional<List<MonthlyDeductionAmountJapEntity>> findAllByCreditCardDeductionJpaEntity_Id(final Long creditCardDeductionId);
}
