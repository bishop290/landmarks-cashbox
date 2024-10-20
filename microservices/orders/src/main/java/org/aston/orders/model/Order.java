package org.aston.orders.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "orders", schema = "orders")
@NoArgsConstructor(force = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer", length = 100)
    private String customer;

    @Column(name = "visitors")
    private Integer visitors;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "landscape", length = 100)
    private String landscape;

    @Enumerated(EnumType.STRING)
    @Column(name = "attraction", length = 100)
    private TypeOfAttraction attraction;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 100)
    private Status status;
}
