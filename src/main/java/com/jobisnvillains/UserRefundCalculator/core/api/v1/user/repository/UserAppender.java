package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.repository;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.storage.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAppender {
    private final UserRepository userRepository;

    @Transactional
    public User saveUser(final User user, BCryptPasswordEncoder bCryptPasswordEncoder) {
        UserJpaEntity userJpaEntity = UserJpaEntity.fromModel(user, bCryptPasswordEncoder);
        return userRepository.save(userJpaEntity).toModel();
    }
}
