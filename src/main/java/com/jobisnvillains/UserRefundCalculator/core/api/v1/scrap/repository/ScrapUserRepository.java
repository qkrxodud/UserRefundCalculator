package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.storage.ScrapUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapUserRepository extends JpaRepository<ScrapUserJpaEntity, Long> {
    Optional<ScrapUserJpaEntity> findByUserId(String userId);
}
