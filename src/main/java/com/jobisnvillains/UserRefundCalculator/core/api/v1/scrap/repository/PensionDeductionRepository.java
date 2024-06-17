package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.PensionDeductionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensionDeductionRepository extends JpaRepository <PensionDeductionJpaEntity, Long> {
}
