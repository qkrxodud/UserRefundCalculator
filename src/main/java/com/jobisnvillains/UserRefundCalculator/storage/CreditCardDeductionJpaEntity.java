package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.CreditCardDeduction;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "credit_card_deduction")
public class CreditCardDeductionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "income_id")
    private IncomeJpaEntity incomeJpaEntity;

    @Column(name="year", nullable = false)
    private int year;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public CreditCardDeductionJpaEntity(Long id, IncomeJpaEntity incomeJpaEntity, int year) {
        this.id = id;
        this.incomeJpaEntity = incomeJpaEntity;
        this.year = year;
    }

    public CreditCardDeductionJpaEntity() {

    }

    public static CreditCardDeductionJpaEntity fromModel(final CreditCardDeduction creditCardDeduction, IncomeJpaEntity incomeJpaEntity) {
        return CreditCardDeductionJpaEntity.builder()
                .year(creditCardDeduction.getYear())
                .incomeJpaEntity(incomeJpaEntity)
                .build();
    }
}
