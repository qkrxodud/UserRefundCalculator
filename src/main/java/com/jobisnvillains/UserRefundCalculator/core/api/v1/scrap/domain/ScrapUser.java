package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler.port.ScrapHandler;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.NotRegisterUserStateException;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ScrapUser {
    private static final Long IS_NOT_REGISTER_SCRAP_MEMBER = -1L;
    private static final String NOT_REGISTER_USER_MSG = "등록되지 않은 사용자입니다.";
    private Long id;
    private String userId;
    private String name;
    private String regNo;
    private Income income;

    @Builder
    public ScrapUser(Long id, String userId, String name, String regNo) {
        this.id = Optional.ofNullable(id)
                .orElse(IS_NOT_REGISTER_SCRAP_MEMBER);
        this.userId = userId;
        this.name = name;
        this.regNo = regNo;
    }

    public void initIncomeInfo(ScrapHandler scrapHandler) {
        income = scrapHandler.getScrapIncomeInfo(name, regNo);
    }

    public void checkNotRegisterUser() {
        if (id == IS_NOT_REGISTER_SCRAP_MEMBER) {
            throw new NotRegisterUserStateException(NOT_REGISTER_USER_MSG);
        }
    }


}
