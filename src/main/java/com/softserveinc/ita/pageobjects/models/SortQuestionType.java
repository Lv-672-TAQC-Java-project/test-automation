package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortQuestionType {
    FROM_BUYER("0: Object"),
    DATE("1: Object"),
    HELPFUL("2: Object"),
    WITH_ATTACHMENTS("3: Object");

    private final String sortOption;
}
