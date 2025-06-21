package com.ayd2.imporcomgua.models.payment;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

import com.ayd2.imporcomgua.models.sale.Sale;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "receiptNumber")
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID receiptNumber;

    @Column
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column
    private String accountNumber;

    @Column
    private String transactionNumber;

    @Column
    private Double amount;
}