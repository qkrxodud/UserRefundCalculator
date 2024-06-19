package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.service;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Tax {
    BRACKET_1(0, 14000000, 0.06, 0),
    BRACKET_2(14000001, 50000000, 0.15, 840000),
    BRACKET_3(50000001, 88000000, 0.24, 6240000),
    BRACKET_4(88000001, 150000000, 0.35, 15360000),
    BRACKET_5(150000001, 300000000, 0.38, 37060000),
    BRACKET_6(300000001, 500000000, 0.40, 94060000),
    BRACKET_7(500000001, 1000000000, 0.42, 174060000),
    BRACKET_8(1000000001, Integer.MAX_VALUE, 0.45, 384060000);

    private final int lowerBound;
    private final int upperBound;
    private final double rate;
    private final int fixedAmount;

    Tax(int lowerBound, int upperBound, double rate, int fixedAmount) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.rate = rate;
        this.fixedAmount = fixedAmount;
    }

    public static Tax getBracket(Double income) {
        return Arrays.stream(values())
                .filter(bracket -> income >= bracket.getLowerBound() && income <= bracket.getUpperBound())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid income: " + income));
    }
}
