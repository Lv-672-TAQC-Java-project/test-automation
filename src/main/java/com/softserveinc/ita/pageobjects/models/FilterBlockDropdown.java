package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterBlockDropdown {
    YEAR(1),
    BRAND(2),
    MODEL(3),
    COMPLETE_SET(4);

    private final int dropdownNumber;
}
