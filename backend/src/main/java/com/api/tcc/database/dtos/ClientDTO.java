package com.api.tcc.database.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ClientDTO {
    @Size(min=3, max=100)
    private String name;
    @Size(min = 1, max = 10)
    private String gender;
    private Date birth;
    @NotBlank
    @CPF
    private String cpf;
}
