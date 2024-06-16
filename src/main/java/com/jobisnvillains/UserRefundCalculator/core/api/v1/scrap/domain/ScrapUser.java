package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller.info.ScrapIncome;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service.handler.ScrapHandler;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScrapUser {
    private Long id;
    private String userId;
    private String name;
    private String regNo;
    private ScrapIncome scrapIncomeInfo;

    @Builder
    public ScrapUser(Long id, String userId, String name, String regNo) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.regNo = regNo;
    }

    public void getIncomeInfo(ScrapHandler scrapHandler) {
        scrapIncomeInfo = scrapHandler.getScrapIncomeInfo(name, regNo);
    }

}
