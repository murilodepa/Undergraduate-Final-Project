package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_CLIENT_IMAGE")
public class ClientImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client_image", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientModel clientModel;
}
