package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PopularBrandName {
    PUMA(""),
    SKECHERS(),
    ECCO(),
    LEVIS(),
    TRENDYOL(),
    TRAUM(),
    HM(),
    COLINS(),
    ASICS(),
    ADIDAS(),
    NIKE(),
    NEWBALANCE();

    private final String popularBrandName;
}
