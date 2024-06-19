package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler.port;


import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;

public interface ScrapHandler {
    Income getScrapIncomeInfo(String name, String regNo);
}
