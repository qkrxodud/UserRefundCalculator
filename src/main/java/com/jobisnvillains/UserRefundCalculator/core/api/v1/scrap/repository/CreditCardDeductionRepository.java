package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.CreditCardDeductionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardDeductionRepository extends JpaRepository<CreditCardDeductionJpaEntity, Long> {
}
