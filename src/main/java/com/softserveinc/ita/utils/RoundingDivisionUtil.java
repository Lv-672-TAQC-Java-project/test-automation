package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoundingDivisionUtil {

    public int getRoundingDivision(int numerator, int denominator) {

        return (int) Math.ceil((double) numerator / denominator);
    }
}
