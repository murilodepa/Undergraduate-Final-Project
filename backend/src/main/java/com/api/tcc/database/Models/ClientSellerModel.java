package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_CLIENT_SELLER")
public class ClientSellerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client_seller", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "time")
    private LocalDateTime dateTime;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientModel clientModel;
    @ManyToOne
    @JoinColumn(name = "id_seller")
    private SellerModel sellerModel;
}
