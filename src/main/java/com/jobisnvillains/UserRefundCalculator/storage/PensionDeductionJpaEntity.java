package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.PensionDeduction;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "pension_deduction")
public class PensionDeductionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "income_id")
    private IncomeJpaEntity incomeJpaEntity;

    @Column(name="month", nullable = false)
    private String month;

    @Column(name="amount", nullable = false)
    private String amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public PensionDeductionJpaEntity(Long id, IncomeJpaEntity incomeJpaEntity, String month, String amount) {
        this.id = id;
        this.incomeJpaEntity = incomeJpaEntity;
        this.month = month;
        this.amount = amount;
    }

    public PensionDeductionJpaEntity() {

    }

    public static PensionDeductionJpaEntity fromModel(final PensionDeduction pensionDeduction, final IncomeJpaEntity incomeJpaEntity) {
        return PensionDeductionJpaEntity.builder()
                .month(pensionDeduction.getMonth())
                .amount(pensionDeduction.getAmount())
                .incomeJpaEntity(incomeJpaEntity)
                .build();

    }
}
