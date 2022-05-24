package com.softserveinc.ita.pageobjects.components;

import com.codeborne.selenide.CollectionCondition;
import com.softserveinc.ita.pageobjects.ReviewsTab;
import com.softserveinc.ita.pageobjects.models.RatingNumber;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class CommonRatingSection {

    @Step("Filtered reviews by rating")
    public ReviewsTab filterReviews(RatingNumber number) {
        $x(format("(//li[contains(@class, 'customer-rating__item')])[%s]//button", number.ordinal() + 1)).click();

        $$x("//div[@class='comment']").shouldHave(CollectionCondition.size(getFilteredReviewsCount(number)), ofSeconds(10));

        return new ReviewsTab();
    }

    public int getReviewsCount() {
        return $$x("//div[@class='comment']")
                .shouldHave(sizeNotEqual(0), ofSeconds(10))
                .size();
    }

    public int getFilteredReviewsCount(RatingNumber number) {

        return getReviewsCountBy(number) == 0 ? getTotalReviewsCount() : getReviewsCountBy(number);
    }

    public int getReviewsCountBy(RatingNumber number) {
        int reviewCountByRatingNumber = Integer.parseInt($x(format("(//span[@class = 'detailed__count'])" +
                "[%s]", number.ordinal() + 1)).text());

        return reviewCountByRatingNumber;
    }

    public int getTotalReviewsCount() {
        var totalReviewsCount = $x("//div[contains(@class, 'rating-total__evaluations')]")
                .text()
                .replaceAll("[^0-9]", "");

        return Integer.parseInt(totalReviewsCount);
    }
}
