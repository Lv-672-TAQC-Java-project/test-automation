package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.ReviewsTab;
import com.softserveinc.ita.pageobjects.models.RatingNumber;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CommonRatingSection {

    @Step("Filtered reviews by rating")
    public ReviewsTab filterReviews(RatingNumber number) {
        $x(format("(//li[contains(@class, 'customer-rating__item')])[%s]//button", number.ordinal() + 1)).click();

        //TODO check if reviews are filtered

        return new ReviewsTab();
    }

    public int getCount(RatingNumber number) {

        return Integer
                .parseInt($x(format("(//span[@class = 'detailed__count'])[%s]", number.ordinal() + 1))
                        .text());
    }
}
