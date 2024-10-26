package org.aston.orders.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "orders")
@NoArgsConstructor(force = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_visitors")
    private Long numberOfVisitors;

    @Column(name = "price")
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 100)
    private Status status;

    @Column(name = "ticket")
    private Long ticket;

    @Column(name = "cash_receipt")
    private Long cashReceipt;
}
