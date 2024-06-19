package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

public class UserJpaEntities {
    private List<UserJpaEntity> userList;

    public UserJpaEntities(List<UserJpaEntity> userList) {
        this.userList = userList;
    }

    public Users getToModel() {
        return new Users(userList.stream()
                .map(userJpaEntity -> userJpaEntity.toModel())
                .collect(Collectors.toList()));

    }
}
