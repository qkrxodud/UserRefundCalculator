package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain;

import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.AlreadyUserStateException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class Users {
    private static final String ALREADY_USER_MSG = "해당주민등록 번호는 이미 등록된 사용자입니다.";

    private List<User> userList;

    public Users(List<User> userList) {
        this.userList = userList;
    }

    public void checkRegNo(String regNo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        boolean userExists = userList.stream()
                .anyMatch(user -> bCryptPasswordEncoder.matches(regNo, user.getRegNo()));

        if (userExists) {
            throw new AlreadyUserStateException(ALREADY_USER_MSG);
        }
    }
}
