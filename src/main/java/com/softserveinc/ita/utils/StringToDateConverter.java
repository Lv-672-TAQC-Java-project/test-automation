package com.softserveinc.ita.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class StringToDateConverter {

    public static Date convertStringToDate(String date) {
        LocalDate localDate = null;
        if (date.matches(".*\\d.*")) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter
                            .ofPattern("dd MMMM uuuu")
                            .withLocale(new Locale("uk"));
            localDate = LocalDate.parse(date, dateTimeFormatter);
        }

        return Date.from(localDate
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }
}
