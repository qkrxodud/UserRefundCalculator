package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service.handler;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller.info.ScrapIncome;

public interface ScrapHandler {
    public ScrapIncome getScrapIncomeInfo(String name, String regNo);
}
