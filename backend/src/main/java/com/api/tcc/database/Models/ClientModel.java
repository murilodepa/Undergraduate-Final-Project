package com.api.tcc.database.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "TB_CLIENT")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "name")
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "gender")
    @Size(min = 1, max = 10)
    private String gender;

    @Column(name = "birth")
    private Date birth;

    @NotNull
    @Column(name = "cpf")
    @CPF
    private String cpf;
}
