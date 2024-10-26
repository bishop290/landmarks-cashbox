package org.aston.payments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "payments")
@NoArgsConstructor(force = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "cancel")
    private Boolean cancel;
}
