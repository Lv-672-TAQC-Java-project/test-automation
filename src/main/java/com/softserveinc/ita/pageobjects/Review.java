package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Review {

    private final String rootElementPath;

    @Step("Get a rating of the review")
    public int getRating() {
        int amountOfFilledStars = $$x(String.format("%s%s", rootElementPath, "//*[name()='path' and @fill='#ffa900']"))
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size();

        return amountOfFilledStars;
    }

    public boolean isRatingDisplayed() {
        String rating = String.format("%s%s", rootElementPath, "//ul[@class='rating-stars']");

        return isDisplayed($x(rating), ofSeconds(10));
    }
}
