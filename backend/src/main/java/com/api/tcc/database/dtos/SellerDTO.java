package com.api.tcc.database.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SellerDTO {
    @Size(min = 3, max = 100)
    private String name;
    @Size(min = 1, max = 10)
    private String gender;
    private String birth;
    @Size(min = 3, max = 50)
    private String sector;
    private Boolean available;
    private int attendances;
    @Email
    private String email;
    @Size(min = 5, max = 30)
    private String password;
}
