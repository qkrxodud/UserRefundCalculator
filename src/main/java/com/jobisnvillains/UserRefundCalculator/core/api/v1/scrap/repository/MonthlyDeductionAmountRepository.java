package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.MonthlyDeductionAmountJapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyDeductionAmountRepository extends JpaRepository<MonthlyDeductionAmountJapEntity, Long> {
}
