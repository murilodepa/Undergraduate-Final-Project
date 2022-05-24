package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_PURCHASE")
public class PurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_purchase", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "category")
    private String category;
    @Column(name = "time")
    private LocalDateTime localDateTime;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientModel clientModel;
}
