package com.api.tcc.database.dtos;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class IdImageNameDTO {
    private long userId;
    @Size(min = 3, max = 100)
    private String name;
    private String profileImage;
}
