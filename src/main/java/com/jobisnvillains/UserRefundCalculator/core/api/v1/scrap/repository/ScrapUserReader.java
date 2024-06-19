package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.ScrapUser;
import com.jobisnvillains.UserRefundCalculator.storage.ScrapUserJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScrapUserReader {
    private final ScrapUserRepository userRepository;

    public ScrapUser findByUserId(final String userId) {
        ScrapUserJpaEntity scrapUserJpaEntity = userRepository.findByUserId(userId)
                .orElse(new ScrapUserJpaEntity());

        return scrapUserJpaEntity.toModel();
    }
}
