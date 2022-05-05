package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductsSectionName {
    LAST_VIEWED_PRODUCTS(1),
    RECOMMENDATIONS_BASED_ON_YOUR_REVIEWS(2),
    MORE_PRODUCTS_TO_CHOOSE_FROM(3);

    private final int sectionIndex;
}
