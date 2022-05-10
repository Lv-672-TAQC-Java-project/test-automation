package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductAvailability {

    AVAILABLE,
    UNAVAILABLE,
    OUT_OF_STOCK
}
