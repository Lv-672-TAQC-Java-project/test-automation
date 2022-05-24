package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShoeSize {
    SIZE_37(37),
    SIZE_37_5(37.5),
    SIZE_38(38),
    SIZE_38_5(38.5),
    SIZE_39(39),
    SIZE_39_5(39.5),
    SIZE_40(40),
    SIZE_40_5(40.5),
    SIZE_41(41),
    SIZE_41_5(41.5),
    SIZE_42(42),
    SIZE_42_5(42.5),
    SIZE_43(43),
    SIZE_43_5(43.5),
    SIZE_44(44),
    SIZE_44_5(44.5),
    SIZE_45(45),
    SIZE_45_5(45.5),
    SIZE_46(46);

    private final double sizeNumber;
}
