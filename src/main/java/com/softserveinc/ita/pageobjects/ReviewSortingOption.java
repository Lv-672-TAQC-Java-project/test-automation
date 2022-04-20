package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReviewSortingOption {

    FROM_BUYER("Від тих, хто купив цей товар"),
    HELPFUL("Найкорисніші"),
    DATE("За датою"),
    WITH_ATTACHMENTS("З фото і відео");

    private final String sortingName;
}
