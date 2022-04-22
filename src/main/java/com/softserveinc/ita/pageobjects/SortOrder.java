package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortOrder {

    FROM_CHEAP("1: cheap"),
    FROM_EXPENSIVE("2: expensive"),
    BY_RELEVANCE("3: relevance");

    private final String sortOrderOption;
}
