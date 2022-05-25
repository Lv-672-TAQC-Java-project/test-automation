package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.ReviewsTab;
import com.softserveinc.ita.pageobjects.models.RatingNumber;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class ReviewsFilteringModal {
    public ReviewsFilteringModal filterReviews(RatingNumber number) {
        $x(format("//p[contains(text(), '%s') and @class = 'comments-filter__label']", number.ordinal() + 1)).click();

        return this;
    }

    public ReviewsTab openReviewsTab() {
        $x("//button[@class = 'button button--medium button--navy']").click();

        $x("//button[@class = 'selected-filters__button']").shouldBe(visible, ofSeconds(10));
        return new ReviewsTab();
    }

}
