package com.jobisnvillains.UserRefundCalculator.storage;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.CreditCardDeduction;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.PensionDeducations;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "Income")
public class IncomeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private ScrapUserJpaEntity scrapUserJpaEntity;

    private Long comprehensiveIncomeAmount;

    private Long taxCredit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public IncomeJpaEntity(Long id, Long comprehensiveIncomeAmount, Long taxCredit, ScrapUserJpaEntity scrapUserJpaEntity) {
        this.id = id;
        this.comprehensiveIncomeAmount = comprehensiveIncomeAmount;
        this.taxCredit = taxCredit;
        this.scrapUserJpaEntity = scrapUserJpaEntity;
    }

    public Income toModel(PensionDeducations pensionDeductions, CreditCardDeduction creditCardDeduction) {
        return Income.builder()
                .id(id)
                .comprehensiveIncomeAmount(comprehensiveIncomeAmount)
                .pensionDeductions(pensionDeductions)
                .creditCardDeduction(creditCardDeduction)
                .taxCredit(taxCredit)
                .build();
    }

    public IncomeJpaEntity() {

    }

    public static IncomeJpaEntity fromModel(final Income income, final ScrapUserJpaEntity scrapUserJpaEntity) {
        return IncomeJpaEntity.builder()
                .comprehensiveIncomeAmount(income.getComprehensiveIncomeAmount())
                .taxCredit(income.getTaxCredit())
                .scrapUserJpaEntity(scrapUserJpaEntity)
                .build();
    }
}
