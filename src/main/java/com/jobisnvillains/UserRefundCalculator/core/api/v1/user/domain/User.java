package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain;

import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.AlreadyUserStateException;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.NotRegisterUserStateException;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class User {
    private static final Long IS_REGISTER_MEMBER = 0L;
    private static final Long IS_NOT_REGISTER_MEMBER = -1L;
    private static final String ALREADY_USER_MSG = "이미등록된 사용자입니다.";
    private static final String IS_NOT_REGISTER_MEMBER_MSG = "이미등록된 사용자입니다.";
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String regNo;

    @Builder
    public User(Long id, String userId, String password, String name, String regNo) {
        this.id = Optional.ofNullable(id)
                .orElse(IS_NOT_REGISTER_MEMBER);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public void checkRegisterUser() {
        if (id > IS_REGISTER_MEMBER) {
            throw new AlreadyUserStateException(ALREADY_USER_MSG);
        }
    }

    public void checkNotRegisterUser() {
        if (id == IS_NOT_REGISTER_MEMBER) {
            throw new NotRegisterUserStateException(ALREADY_USER_MSG);
        }
    }

}
