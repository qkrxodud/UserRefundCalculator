package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "member")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;
    private String name;
    private String regNo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public UserJpaEntity(Long id, String userId, String password, String name, String regNo) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
    }

    public UserJpaEntity() {
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .userId(userId)
                .password(password)
                .name(name)
                .regNo(regNo)
                .build();
    }

    public static UserJpaEntity fromModel(final User user, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        return UserJpaEntity.builder()
                .userId(user.getUserId())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .regNo(bCryptPasswordEncoder.encode(user.getRegNo()))
                .build();
    }
}
