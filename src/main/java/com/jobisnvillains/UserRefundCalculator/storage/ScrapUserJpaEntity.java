package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.ScrapUser;
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
public class ScrapUserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String name;
    private String regNo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public ScrapUserJpaEntity(Long id, String userId, String name, String regNo) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.regNo = regNo;
    }

    public ScrapUserJpaEntity() {
    }

    public ScrapUser toModel() {
        return ScrapUser.builder()
                .id(id)
                .name(name)
                .regNo(regNo)
                .build();
    }

    public static ScrapUserJpaEntity fromModel(final ScrapUser scrapUser) {
        return ScrapUserJpaEntity.builder()
                .id(scrapUser.getId())
                .userId(scrapUser.getUserId())
                .name(scrapUser.getName())
                .regNo(scrapUser.getRegNo())
                .build();
    }
}
