package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PopularBrandName {
    PUMA("Puma"),
    SKECHERS("Skechers"),
    ECCO("Ecco"),
    LEVIS("Levi's"),
    TRENDYOL("Trendyol"),
    TRAUM("Traum"),
    HM("H&M"),
    COLINS("Colin’s"),
    ASICS("Asics"),
    ADIDAS("Adidas"),
    NIKE("Nike"),
    NEWBALANCE("New Balance");

    private final String popularBrandName;
}
