package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.param;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.RegNoIllegalStateException;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class UserParam {
    private static final Pattern REG_NO_PATTERN = Pattern.compile("^(\\d{2})(\\d{2})(\\d{2})-(\\d)(\\d{6})$");
    private static final String REG_NO_EMPTY = "주민등록번호가 비워있습니다.";
    private static final String REG_NO_MISS_MATCH = "잘못된 주민등록번호 입니다.";
    private String userId;
    private String password;
    private String name;
    private String regNo;

    public User toModel() {
        checkRegNoValidation(regNo);

        return User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .regNo(regNo)
                .build();
    }

    private void checkRegNoValidation(final String regNo) {
        if (!StringUtils.hasText(regNo)) {
            throw new RegNoIllegalStateException(REG_NO_EMPTY);
        }

        Matcher matcher = REG_NO_PATTERN.matcher(regNo);
        if (!matcher.matches()) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
        checkRegNoDate(matcher);
        checkGender(matcher);
    }

    private void checkRegNoDate(final Matcher matcher) {
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));

        checkMonth(month);
        checkDay(day);
        checkMonthToDay(month, day);
        // 윤년 체크
        checkLeapYear(year, month, day);
    }

    private void checkMonth(final int month) {
        if (month < 1 || month > 12) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
    }

    private void checkDay(final int day) {
        if (day < 1 || day > 31) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
    }

    private void checkMonthToDay(final int month, final int day) {
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
    }

    private void checkLeapYear(final int year, final int month, final int day) {
        if (month != 2) {
            return;
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        if (day > (isLeapYear ? 29 : 28)) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
    }
    
    private void checkGender(final Matcher matcher) {
        // 1 1900년대 출생한 남자
        // 2 1900년대 출생한 여자
        // 3 2000년대 출생한 남자
        // 4 2000년대 출생한 여자
        // 5 1900년대 출생한 외국인 남자
        // 6 1900년대 출생한 외국인 여자
        // 7 2000년대 출생한 외국인 남자
        // 8 2000년대 출생한 외국인 여자

        int gender = Integer.parseInt(matcher.group(4));
        if (gender < 1 || gender > 8) {
            throw new RegNoIllegalStateException(REG_NO_MISS_MATCH);
        }
    }
}
