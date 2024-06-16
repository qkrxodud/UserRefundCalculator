package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.ScrapUser;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.SracpUserReader;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service.handler.ScrapHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final SracpUserReader reader;
    private final ScrapHandler scrapHandler;

    public void saveIncomeInfo(final String userId) {
        ScrapUser byUserId = reader.findByUserId(userId);
        byUserId.getIncomeInfo(scrapHandler);
    }
}
