package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.repository;

import com.jobisnvillains.UserRefundCalculator.storage.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByUserId(String userId);
    Optional<UserJpaEntity> findByRegNo(String regNo);
}
