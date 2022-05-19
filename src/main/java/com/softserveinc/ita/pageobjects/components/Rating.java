package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.ReviewsTab;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class Rating {
    private final String rootElementPath;

    @Step("Filtered reviews by rating")
    public ReviewsTab filterReviews() {
        $x(rootElementPath + "//button").click();

        $x("//button[contains(@class, 'product-comments__show-all')]").shouldBe(visible);
        return new ReviewsTab();
    }

    public int getCount() {
        return Integer
                .parseInt($x("//span[@class = 'detailed__count']")
                        .text());
    }
}
