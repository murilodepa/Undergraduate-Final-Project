package com.api.tcc.database.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "TB_SELLER")
public class SellerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_seller", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "name")
    @Size(min = 3, max = 100)
    private String name;
    @Column(name = "gender")
    @Size(min = 1, max = 10)
    private String gender;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "sector")
    @Size(min = 3, max = 20)
    private String sector;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "attendances")
    private int attendances;
    @NotNull
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "cpf")
    @CPF
    private String cpf;
}