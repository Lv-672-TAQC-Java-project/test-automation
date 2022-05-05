package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionSortingOption {
    FROM_BUYER(0),
    DATE(1),
    HELPFUL(2),
    WITH_ATTACHMENTS(3);

    private final int sortOption;
}
