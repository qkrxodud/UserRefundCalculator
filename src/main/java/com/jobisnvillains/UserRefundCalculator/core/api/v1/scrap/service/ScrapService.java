package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.ScrapUser;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.IncomeAppender;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.SracpUserReader;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler.ScrapHandler;
import com.jobisnvillains.UserRefundCalculator.storage.IncomeJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapHandler scrapHandler;
    private final SracpUserReader reader;
    private final IncomeAppender incomeAppender;

    public void saveIncomeInfo(final String userId) {
        ScrapUser scrapUser = reader.findByUserId(userId);
        scrapUser.initIncomeInfo(scrapHandler);
        incomeAppender.saveIncome(scrapUser);
    }
}
