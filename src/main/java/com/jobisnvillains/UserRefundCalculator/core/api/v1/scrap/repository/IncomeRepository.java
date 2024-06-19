package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.IncomeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<IncomeJpaEntity, Long> {
    Optional<IncomeJpaEntity> findByScrapUserJpaEntity_Id(final Long scrapUserId);
}
