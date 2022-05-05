package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@UtilityClass
public class DateUtil {

    public static Date toDate(String date) {
        //Contains a re-check because today's date is written in
        // word "сьогодні"("сегодня") and yesterday's - "вчора"("вчера")
        // checked if word contain cyrillic letter "с"
        LocalDate localDate;

        if (date.matches(".*\\d.*")) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter
                            .ofPattern("dd MMMM uuuu")
                            .withLocale(new Locale("uk"));
            localDate = LocalDate.parse(date, dateTimeFormatter);
        } else if (date.contains("с")) {
            localDate = LocalDate.now();
        } else {
            localDate = LocalDate.now().minusDays(1);
        }

        return Date.from(localDate
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }
}
