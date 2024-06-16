package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScrapUserAppender {
    private final ScrapUserRepository userRepository;

}
