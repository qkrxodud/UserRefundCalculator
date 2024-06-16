package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler;

import com.jobisnvillains.UserRefundCalculator.mock.FakeScrapHandler;
import org.junit.jupiter.api.Test;

class ScrapHandlerImplTest {

    @Test
    void 스크랩_정보조회_API_테스트() {
        FakeScrapHandler fakeScrapHandler = new FakeScrapHandler();
        fakeScrapHandler.getScrapIncomeInfo("동탁", "921108-1582816");
        fakeScrapHandler.getScrapIncomeInfo("관우", "681108-1582816");
        fakeScrapHandler.getScrapIncomeInfo("손권", "890601-2455116");
        fakeScrapHandler.getScrapIncomeInfo("유비", "790411-1656116");
        fakeScrapHandler.getScrapIncomeInfo("조조", "810326-2715702");
    }
}