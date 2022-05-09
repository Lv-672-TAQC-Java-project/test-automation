package com.softserveinc.ita.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import static java.time.LocalDate.*;
import static java.time.format.DateTimeFormatter.*;

@UtilityClass
public class DateUtil {

    public static Date toDate(String date) {
        //Contains a re-check because today's date is written in
        // word "сьогодні"("сегодня") and yesterday's - "вчора"("вчера")
        // checked if word contain cyrillic letter "с"
        LocalDate localDate;

        if (date.matches(".*\\d.*")) {
            var dateTimeFormatter = ofPattern("dd MMMM uuuu")
                    .withLocale(new Locale("uk"));
            localDate = parse(date, dateTimeFormatter);
        } else if (date.contains("с")) {
            localDate = now();
        } else {
            localDate = now().minusDays(1);
        }

        return Date.from(localDate
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }
}
