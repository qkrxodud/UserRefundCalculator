package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.param;

import lombok.Data;

@Data
public class UserLoginParam {
    private String userId;
    private String password;
}
