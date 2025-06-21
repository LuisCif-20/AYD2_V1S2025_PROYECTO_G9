package com.ayd2.imporcomgua.models.sale;

import com.ayd2.imporcomgua.models.client.Client;
import com.ayd2.imporcomgua.models.salesman.Salesman;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "warehouse_exit_date")
    private LocalDate warehouseExitDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "shipment_number")
    private UUID shipmentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "credit_days")
    private Integer creditDays;

    @ManyToOne
    @JoinColumn(name = "salesman_code")
    private Salesman salesman;

    @Column(name = "dte_invoice_number")
    private Integer dteInvoiceNumber;

    @Column(name = "invoice_name")
    private String invoiceName;

    @Column(name = "invoice_nit")
    private String invoiceNit;

    @Column
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "sale_status")
    private SaleStatus saleStatus;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column
    private String notes;

    @Column
    private Double remainingBalance;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetail> details = new ArrayList<>();
}