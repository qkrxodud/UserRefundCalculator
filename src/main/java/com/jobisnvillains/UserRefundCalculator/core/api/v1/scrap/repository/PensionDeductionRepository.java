package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.PensionDeductionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PensionDeductionRepository extends JpaRepository <PensionDeductionJpaEntity, Long> {
    Optional<List<PensionDeductionJpaEntity>> findAllByIncomeJpaEntity_Id(final Long incomeId);
}
