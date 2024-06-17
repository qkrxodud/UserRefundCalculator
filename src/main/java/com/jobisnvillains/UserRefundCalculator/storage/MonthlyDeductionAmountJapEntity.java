package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.MonthlyDeductionAmount;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "monthly_deduction_amount")
public class MonthlyDeductionAmountJapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_card_deduction_id")
    private CreditCardDeductionJpaEntity creditCardDeductionJpaEntity;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "deduction", nullable = false)
    private String deduction;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public MonthlyDeductionAmountJapEntity(Long id, CreditCardDeductionJpaEntity creditCardDeductionJpaEntity, String month, String deduction) {
        this.id = id;
        this.creditCardDeductionJpaEntity = creditCardDeductionJpaEntity;
        this.month = month;
        this.deduction = deduction;
    }

    public MonthlyDeductionAmountJapEntity() {

    }

    public static MonthlyDeductionAmountJapEntity fromModel(final MonthlyDeductionAmount monthlyDeductionAmount ,
                                                            final CreditCardDeductionJpaEntity creditCardDeductionJpaEntity) {
        return MonthlyDeductionAmountJapEntity.builder()
                .month(monthlyDeductionAmount.getMonth())
                .deduction(monthlyDeductionAmount.getDeduction())
                .creditCardDeductionJpaEntity(creditCardDeductionJpaEntity)
                .build();
    }

}
