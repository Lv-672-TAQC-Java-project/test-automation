package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionSortingOption {
    FROM_BUYER,
    DATE,
    HELPFUL,
    WITH_ATTACHMENTS;
}
