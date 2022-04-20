package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortingOption {

    FROM_BUYER("from_buyer"),
    HELPFUL("helpful"),
    DATE("date"),
    WITH_ATTACHMENTS("with_attachments");

    private final String optionName;
}
