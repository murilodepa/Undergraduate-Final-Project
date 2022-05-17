package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "TB_CLIENT_IMAGE")
public class ClientImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client_image", updatable = false, unique = true, nullable = false)
    private long id;
    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE)
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientModel clientModel;
}
