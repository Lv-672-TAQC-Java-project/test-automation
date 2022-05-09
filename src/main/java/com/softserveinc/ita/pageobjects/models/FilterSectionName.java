package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterSectionName {
    MANUFACTURER("producer"),
    DISPLAY_TYPE("31565"),
    PRODUCT_AVAILABILITY("sell_status"),
    MATURATION_PERIOD("viderjka-krepkie-napitki");

    private final String filterSectionPath;
}
