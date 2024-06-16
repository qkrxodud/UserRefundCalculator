package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AccessToken {
    private Map<String, String> accessTokens = new HashMap<>();
    public void addAccessToken(final String key, final String value) {
        accessTokens.put(key, value);
    }
}
