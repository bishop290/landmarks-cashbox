package org.aston.tickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "tickets", schema = "tickets")
@NoArgsConstructor(force = true)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "landscape", length = 100)
    private String landscape;

    @Column(name = "amount")
    private Long amount;
}
