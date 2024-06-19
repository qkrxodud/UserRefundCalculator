package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.service;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.NotDefineUserStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class CheckValidUserService {
    private static final String NOT_DEFINE_USER = "정의되지 않은 사용자입니다.";
    private static final Map<String, String> DEFINE_USERS = new HashMap<>() {{
        put("동탁", "921108-1582816");
        put("관우", "681108-1582816");
        put("손권", "890601-2455116");
        put("유비", "790411-1656116");
        put("조조", "810326-2715702");
    }};

    public void checkDefinedUser(final User user) {
        String defineRegNo = DEFINE_USERS.get(user.getName());
        if (defineRegNo == null || !defineRegNo.equals(user.getRegNo())) {
            throw new NotDefineUserStateException(NOT_DEFINE_USER);
        }
    }
}
