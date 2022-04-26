package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductAvailability {

    UNAVAILABLE("unavailable"),
    OUT_OF_STOCK("out_of_stock");

    private final String availabilityName;
}
