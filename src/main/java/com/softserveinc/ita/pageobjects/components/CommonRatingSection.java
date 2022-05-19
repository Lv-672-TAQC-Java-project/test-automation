package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.models.RatingNumber;

import static java.lang.String.format;

public class CommonRatingSection {

    public Rating getRating(RatingNumber number) {

        return new Rating(format("(//li[contains(@class, 'customer-rating__item')])[%s]", number.ordinal() + 1));
    }
}
