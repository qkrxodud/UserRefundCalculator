package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler;


import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;

public interface ScrapHandler {
    public Income getScrapIncomeInfo(String name, String regNo);
}
