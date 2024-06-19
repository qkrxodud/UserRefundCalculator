package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.CreditCardDeduction;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.MonthlyDeductionAmounts;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

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

    public CreditCardDeduction toModel(final MonthlyDeductionAmounts monthlyDeductionAmounts) {
        return CreditCardDeduction.builder()
                .year(year)
                .monthlyDeductionAmounts(monthlyDeductionAmounts)
                .build();
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
