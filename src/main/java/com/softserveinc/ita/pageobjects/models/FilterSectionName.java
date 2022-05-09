package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterSectionName {
    MANUFACTURER("producer"),
    DISPLAY_TYPE("31565"),
    PRODUCT_AVAILABILITY("sell_status"),
    PROCESSOR("processor"),
    SCREEN_DIAGONAL("20861"),
    RANDOM_ACCESS_MEMORY_AMOUNT("20863");

    private final String filterSectionPath;
}
