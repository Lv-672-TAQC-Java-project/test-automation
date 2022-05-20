package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShoeSize {
    SHOE_SIZE_37("37"),
    SHOE_SIZE_37_5("37.5"),
    SHOE_SIZE_38("38"),
    SHOE_SIZE_38_5("38.5"),
    SHOE_SIZE_39("39"),
    SHOE_SIZE_39_5("39.5"),
    SHOE_SIZE_40("40"),
    SHOE_SIZE_40_5("40.5"),
    SHOE_SIZE_41("41"),
    SHOE_SIZE_41_5("41.5"),
    SHOE_SIZE_42("42"),
    SHOE_SIZE_42_5("42.5"),
    SHOE_SIZE_43("43"),
    SHOE_SIZE_43_5("43.5"),
    SHOE_SIZE_44("44"),
    SHOE_SIZE_44_5("44.5"),
    SHOE_SIZE_45("45"),
    SHOE_SIZE_45_5("45.5"),
    SHOE_SIZE_46("46");

    private final String path;
}
