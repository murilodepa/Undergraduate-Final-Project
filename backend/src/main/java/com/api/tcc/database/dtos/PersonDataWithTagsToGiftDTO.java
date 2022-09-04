/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.database.dtos;

import lombok.Data;

/**
 * @author Murilo de Paula Araujo
 */
@Data
public class PersonDataWithTagsToGiftDTO {
    private String gender;
    private String age;
    private String size;
    private boolean selectedSummer;
    private boolean selectedWinter;
    private boolean selectedSocial;
    private boolean selectedCasual;
    private boolean selectedPatterned;
    private boolean selectedStripe;
}