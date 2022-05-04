package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductDetailsTabName {
    ALL_ABOUT_PRODUCT(1),
    CHARACTERISTIC(2),
    REVIEWS(3),
    QUESTION(4),
    VIDEO(5),
    PHOTO(6),
    BUY_TOGETHER(7);

    private final int tabId;
}
