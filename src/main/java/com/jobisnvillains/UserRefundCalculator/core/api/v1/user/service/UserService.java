package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.service;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.Users;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.service.CheckValidUserService;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.repository.UserAppender;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.repository.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final CheckValidUserService checkValidUserService;
    private final UserAppender userAppender;
    private final UserReader userReader;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User sinUp(final User user) {
        checkValidUserService.checkDefinedUser(user);
        User byUserId = userReader.findByUserId(user.getUserId());
        byUserId.checkRegisterUser();

        User byRegNo = userReader.findByRegNo(user.getRegNo());
        byRegNo.checkRegisterUser();

        return userAppender.saveUser(user, bCryptPasswordEncoder);
    }

    public boolean login(final String userId, final String password) {
        User byUserId = userReader.findByUserId(userId);
        byUserId.checkNotRegisterUser();
        return bCryptPasswordEncoder.matches(password, byUserId.getPassword());
    }
}
