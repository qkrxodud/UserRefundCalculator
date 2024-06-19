package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.ScrapUser;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.service.TaxService;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler.port.ScrapHandler;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.IncomeAppender;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.IncomeReader;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.repository.ScrapUserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapHandler scrapHandler;
    private final ScrapUserReader scrapUserReader;
    private final IncomeAppender incomeAppender;
    private final IncomeReader incomeReader;
    private final TaxService taxService;

    public void saveIncomeInfo(final String userId) {
        ScrapUser scrapUser = scrapUserReader.findByUserId(userId);
        scrapUser.initIncomeInfo(scrapHandler);
        incomeAppender.saveIncome(scrapUser);
    }

    public String calculateTax(final String userId) {
        ScrapUser scrapUser = scrapUserReader.findByUserId(userId);
        scrapUser.checkNotRegisterUser();

        Income income = incomeReader.findByIncome(scrapUser.getId());
        income.checkIncome();
        return income.calculatedTaxAmount(taxService);
    }
}
