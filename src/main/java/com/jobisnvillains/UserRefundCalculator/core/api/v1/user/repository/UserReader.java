package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.repository;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.Users;
import com.jobisnvillains.UserRefundCalculator.storage.UserJpaEntities;
import com.jobisnvillains.UserRefundCalculator.storage.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public User findByUserId(final String userId) {
        UserJpaEntity userJpaEntity = userRepository.findByUserId(userId)
                .orElse(new UserJpaEntity());

        return userJpaEntity.toModel();
    }

    public User findByRegNo(final String regNo) {
        UserJpaEntity userJpaEntity = userRepository.findByRegNo(regNo)
                .orElse(new UserJpaEntity());

        return userJpaEntity.toModel();
    }

    public Users findAll() {
        UserJpaEntities userJpaEntities = new UserJpaEntities(userRepository.findAll());
        return userJpaEntities.getToModel();
    }
}
