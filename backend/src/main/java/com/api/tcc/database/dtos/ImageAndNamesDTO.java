package com.api.tcc.database.dtos;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ImageAndNamesDTO {
    @Size(min = 3, max = 100)
    private String name;
    private String profileImage;
}
